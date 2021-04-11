package com.example.developerslife.application

import android.app.Application
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.KoinExperimentalAPI
import org.koin.core.context.startKoin

@KoinExperimentalAPI
@ExperimentalSerializationApi
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            fragmentFactory()
            modules(networkModule, viewModelModule, fragmentModule)
        }
    }
}