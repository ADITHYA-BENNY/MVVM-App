package com.example.mvvmapplication.ui.home.cars

import androidx.lifecycle.ViewModel
import com.example.mvvmapplication.data.repositories.CarsRepository
import com.example.mvvmapplication.util.lazyDeferred

class CarsViewModel(
    repository: CarsRepository
) : ViewModel() {
    val cars by lazyDeferred {
        repository.getCars()
    }
}