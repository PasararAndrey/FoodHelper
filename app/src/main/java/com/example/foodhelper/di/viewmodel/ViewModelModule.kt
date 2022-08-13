package com.example.foodhelper.di.viewmodel

import androidx.lifecycle.ViewModel
import com.example.foodhelper.ui.recipedetails.RecipeDetailsViewModel
import com.example.foodhelper.ui.searchrecipes.RecipeSearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RecipeSearchViewModel::class)
    abstract fun bindRecipeSearchViewModel(viewmodel: RecipeSearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RecipeDetailsViewModel::class)
    abstract fun bindRecipeDetailsViewModel(viewModel: RecipeDetailsViewModel): ViewModel
}