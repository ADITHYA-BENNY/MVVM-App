package com.example.mvvmapplication.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmapplication.data.db.AppDatabase
import com.example.mvvmapplication.data.db.entities.Car
import com.example.mvvmapplication.data.network.MyApi
import com.example.mvvmapplication.data.network.SafeApiRequest
import com.example.mvvmapplication.data.preferences.PreferenceProvider
import com.example.mvvmapplication.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private val MINIMUM_INTERVAL = 6

class CarsRepository(
    private val api: MyApi,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
) : SafeApiRequest() {
    private val cars = MutableLiveData<List<Car>>()

    init {
        cars.observeForever {
            saveCars(it)
        }
    }

    suspend fun getCars() : LiveData<List<Car>> {
        return withContext(Dispatchers.IO) {
            fetchCars()
            db.getCarDao().getCars()
        }
    }

    private suspend fun fetchCars() {
        val lastSavedAt = prefs.getLastSavedAt()
        if (lastSavedAt == null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))) {
            val response = apiRequest { api.getCars() }
            cars.postValue(response.cars)
        }
    }

    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(savedAt, LocalDateTime.now()) > MINIMUM_INTERVAL
    }

    private fun saveCars(cars: List<Car>) {
        Coroutines.io{
            prefs.savelastSavedAt(LocalDateTime.now().toString())
            db.getCarDao().saveAllCars(cars)
        }
    }
}