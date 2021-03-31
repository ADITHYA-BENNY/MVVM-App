package com.example.mvvmapplication.ui.home.cars

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmapplication.data.repositories.CarsRepository

@Suppress("UNCHECKED_CAST")
class CarsViewModelFactory(
    private val repository: CarsRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CarsViewModel(repository) as T
    }
}