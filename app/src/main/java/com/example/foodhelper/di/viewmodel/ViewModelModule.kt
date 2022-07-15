package com.example.foodhelper.di.viewmodel

import androidx.lifecycle.ViewModel
import com.example.foodhelper.ui.search.RecipeSearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RecipeSearchViewModel::class)
    abstract fun bindRecipeSearchViewModel(viewmodel: RecipeSearchViewModel): ViewModel
}