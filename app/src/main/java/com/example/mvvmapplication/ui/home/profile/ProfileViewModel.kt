package com.example.mvvmapplication.ui.home.profile

import androidx.lifecycle.ViewModel
import com.example.mvvmapplication.data.repositories.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {
    val user = repository.getUser()
}