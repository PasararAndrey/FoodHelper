package com.example.foodhelper.ui.recipedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodhelper.data.RecipeRepository
import com.example.foodhelper.model.*
import com.example.foodhelper.util.getRecipeIngredientsByMeasure
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class RecipeDetailsViewModel @Inject constructor(private val recipeRepository: RecipeRepository) : ViewModel() {

    private var _overview = MutableLiveData<RecipeGeneral>()
    val overview: LiveData<RecipeGeneral> get() = _overview

    private var ingredientsData: List<RecipeIngredientWithMeasures> = emptyList()

    private var _ingredients = MutableLiveData<List<RecipeIngredient>>()
    val ingredients: LiveData<List<RecipeIngredient>> get() = _ingredients

    private var _steps = MutableLiveData<List<RecipeStep>>()
    val steps: LiveData<List<RecipeStep>> get() = _steps


    private var currentServings = 1
    private var currentMeasure = Measure.METRIC

    fun getRecipe(recipeId: Int) {
        viewModelScope.launch {
            val recipe: RecipeDetails? = recipeRepository.getRecipeDetails(recipeId)
            if (recipe != null) {
                Timber.tag(TAG).d("Result of getting recipe by id. General info \n${recipe.general}")
                _overview.value = recipe.general
                ingredientsData = recipe.ingredients
                _steps.value = recipe.steps
                currentServings = recipe.general.servings
            }
        }
    }

    fun updateServings(newServingsAmount: Int) {
        currentServings = newServingsAmount
        Timber.tag(TAG).d("Updated value of serving: $currentServings")
        updateIngredients()
    }

    private fun updateIngredients() {
        Timber.tag(TAG).d("Default first ingredient measure before all: ${ingredientsData.first().toStringMeasure()}")
        val ingredientsByMeasure: List<RecipeIngredient> = ingredientsData.getRecipeIngredientsByMeasure(currentMeasure)
        Timber.tag(TAG).d("Default first ingredient measure after measuring: ${ingredientsData.first().toStringMeasure()}")
        val ingredients = calculateIngredientsAmount(ingredientsByMeasure)
        Timber.tag(TAG).d("Default first ingredient measure after servings calculation: ${ingredientsData.first().toStringMeasure()}")
        _ingredients.value = ingredients
        Timber.tag(TAG).d("Default first ingredient measure after value updating: ${ingredientsData.first().toStringMeasure()}")
    }


    fun updateMeasure(measure: Measure) {
        currentMeasure = measure
        updateIngredients()
    }

    private fun calculateIngredientsAmount(
        ingredients: List<RecipeIngredient>,
    ): List<RecipeIngredient> {
        val mIngredients = ingredients.map { it.copy() }

        val initialServings = _overview.value?.servings ?: kotlin.run {
            Timber.tag(TAG).d("No initial servings provided")
            return emptyList()
        }
        mIngredients.forEach { recipeIngredient: RecipeIngredient ->
            recipeIngredient.ingredientMeasure.amount = recipeIngredient.ingredientMeasure.amount / initialServings * currentServings
        }
        return mIngredients
    }

    companion object {
        private const val TAG = "Details ViewModel"
    }
}

