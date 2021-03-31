package com.example.mvvmapplication.data.network.responses

import com.example.mvvmapplication.data.db.entities.Car

data class CarsResponse(
    val isSuccessful: Boolean,
    val cars: List<Car>
)