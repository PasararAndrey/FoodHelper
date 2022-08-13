package com.example.foodhelper.ui.recipedetails

import androidx.lifecycle.*
import com.example.foodhelper.data.RecipeRepository
import com.example.foodhelper.model.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class RecipeDetailsViewModel @Inject constructor(private val recipeRepository: RecipeRepository) : ViewModel() {

    private var _currentOverview = MutableLiveData<RecipeGeneral>()
    val currentOverview: LiveData<RecipeGeneral> get() = _currentOverview

    private var _currentNutrition = MutableLiveData<List<RecipeNutrition>>()
    val currentNutrition: LiveData<List<RecipeNutrition>> get() = _currentNutrition

    private var _currentIngredients = MutableLiveData<List<RecipeIngredient>>()
    val currentIngredients: LiveData<List<RecipeIngredient>> get() = _currentIngredients

    private var _currentSteps = MutableLiveData<List<RecipeStep>>()
    val currentSteps: LiveData<List<RecipeStep>> get() = _currentSteps


    fun getRecipe(recipeId: Int) {
        viewModelScope.launch {
            val recipe: RecipeDetails? = recipeRepository.getRecipeDetails(recipeId)
            if (recipe != null) {
                Timber.tag(TAG).d("Get recipe general: ${recipe.general}")
                _currentOverview.value = recipe.general
                _currentNutrition.value = recipe.nutritions
                _currentIngredients.value = recipe.ingredients
                _currentSteps.value = recipe.steps
            }
        }
    }


    companion object {
        private const val TAG = "Details ViewModel"
    }
}