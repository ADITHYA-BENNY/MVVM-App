package com.example.mvvmapplication.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmapplication.data.db.entities.Car

@Dao
interface CarDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllCars(cars: List<Car>)

    @Query("SELECT * FROM  Car")
    fun getCars() : LiveData<List<Car>>
}