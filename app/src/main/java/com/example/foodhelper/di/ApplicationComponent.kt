package com.example.foodhelper.di

import com.example.foodhelper.di.viewmodel.ViewModelFactoryModule
import com.example.foodhelper.di.viewmodel.ViewModelModule
import com.example.foodhelper.ui.recipedetails.*
import com.example.foodhelper.ui.recipedetails.overview.OverviewFragment
import com.example.foodhelper.ui.recipedetails.steps.StepsFragment
import com.example.foodhelper.ui.searchrecipes.FilterSearchFragment
import com.example.foodhelper.ui.searchrecipes.RecipeSearchFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        ViewModelFactoryModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        DispatcherModule::class
    ]
)
interface ApplicationComponent {
    fun inject(filterSearchFragment: FilterSearchFragment)
    fun inject(recipeSearchFragment: RecipeSearchFragment)
    fun inject(recipeDetailsFragment: RecipeDetailsFragment)
    fun inject(overviewFragment: OverviewFragment)
    fun inject(ingredientsFragment: IngredientsFragment)
    fun inject(stepsFragment: StepsFragment)
    fun inject(nutritionFragment: NutritionFragment)
}