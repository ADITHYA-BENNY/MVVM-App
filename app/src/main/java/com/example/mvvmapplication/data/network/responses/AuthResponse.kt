package com.example.mvvmapplication.data.network.responses

import com.example.mvvmapplication.data.db.entities.User

data class AuthResponse(
    val isSuccessful: Boolean?,
    val message: String?,
    val user: User?
)