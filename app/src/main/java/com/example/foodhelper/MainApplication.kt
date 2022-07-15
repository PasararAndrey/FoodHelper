package com.example.foodhelper

import android.app.Application
import com.example.foodhelper.di.ApplicationComponent
import com.example.foodhelper.di.DaggerApplicationComponent

class MainApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.create()
    }
}