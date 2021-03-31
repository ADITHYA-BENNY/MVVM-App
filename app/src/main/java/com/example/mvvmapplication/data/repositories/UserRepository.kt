package com.example.mvvmapplication.data.repositories

import com.example.mvvmapplication.data.db.AppDatabase
import com.example.mvvmapplication.data.db.entities.User
import com.example.mvvmapplication.data.network.MyApi
import com.example.mvvmapplication.data.network.SafeApiRequest
import com.example.mvvmapplication.data.network.responses.AuthResponse

class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {
    suspend fun userLogin(email: String, password: String) : AuthResponse {
        return apiRequest { api.userLogin(email, password) }
    }

    suspend fun userSignUp(name: String, email: String, password: String) : AuthResponse{
        return apiRequest { api.userSignup(name, email, password) }
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()
}