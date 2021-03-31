package com.example.mvvmapplication.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Car(
    @PrimaryKey(autoGenerate = false)
    val index: Int,
    val modelName: String,
    val modelYear: String
)