package com.example.foodhelper

import android.app.Application
import com.example.foodhelper.di.ApplicationComponent
import com.example.foodhelper.di.DaggerApplicationComponent
import timber.log.Timber

class MainApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        applicationComponent = DaggerApplicationComponent.create()
    }
}