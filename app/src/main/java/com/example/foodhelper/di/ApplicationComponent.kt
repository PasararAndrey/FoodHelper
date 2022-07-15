package com.example.foodhelper.di

import com.example.foodhelper.di.viewmodel.ViewModelFactoryModule
import com.example.foodhelper.di.viewmodel.ViewModelModule
import com.example.foodhelper.ui.search.FilterSearchFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        ViewModelFactoryModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {
    fun inject(filterSearchFragment: FilterSearchFragment)
}