package com.example.mvvmapplication

import android.app.Application
import com.example.mvvmapplication.data.db.AppDatabase
import com.example.mvvmapplication.data.network.MyApi
import com.example.mvvmapplication.data.network.NetworkConnectionInterceptor
import com.example.mvvmapplication.data.preferences.PreferenceProvider
import com.example.mvvmapplication.data.repositories.CarsRepository
import com.example.mvvmapplication.data.repositories.UserRepository
import com.example.mvvmapplication.ui.auth.AuthViewModelFactory
import com.example.mvvmapplication.ui.home.cars.CarsViewModelFactory
import com.example.mvvmapplication.ui.home.profile.ProfileViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))


        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from singleton { CarsRepository(instance(), instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider { CarsViewModelFactory(instance()) }
    }
}