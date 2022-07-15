package com.example.foodhelper.util

import android.content.Context
import com.example.foodhelper.MainApplication
import com.example.foodhelper.di.ApplicationComponent

val Context.applicationComponent: ApplicationComponent
    get() = when (this) {
        is MainApplication -> applicationComponent
        else -> this.applicationContext.applicationComponent
    }