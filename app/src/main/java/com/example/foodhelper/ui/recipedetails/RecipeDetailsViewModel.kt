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

    private var _nutrition = MutableLiveData<List<RecipeNutrition>>()
    val nutrition: LiveData<List<RecipeNutrition>> get() = _nutrition

    private var ingredientsData: List<RecipeIngredientWithMeasures> = emptyList()

    private var _ingredients = MutableLiveData<List<RecipeIngredient>>()
    val ingredients: LiveData<List<RecipeIngredient>> get() = _ingredients

    private var _steps = MutableLiveData<List<RecipeStep>>()
    val steps: LiveData<List<RecipeStep>> get() = _steps

    private var _servings = 1
    val servings: Int get() = _servings


    fun getRecipe(recipeId: Int) {
        viewModelScope.launch {
            val recipe: RecipeDetails? = recipeRepository.getRecipeDetails(recipeId)
            if (recipe != null) {
                Timber.tag(TAG).d("Get recipe general: ${recipe.general}")
                _overview.value = recipe.general
                _nutrition.value = recipe.nutritions
                ingredientsData = recipe.ingredients
                _steps.value = recipe.steps
                _servings = recipe.general.servings
            }
        }
    }

    fun updateMeasureState(measure: Measure) {
        val ingredientsByMeasure: List<RecipeIngredient> = ingredientsData.getRecipeIngredientsByMeasure(measure)
        val ingredients = calculateIngredientsAmountByServings(_servings, _servings, ingredientsByMeasure)
        _ingredients.value = ingredients
        Timber.tag(TAG).d(measure.toString())
        Timber.tag(TAG).d(_ingredients.value.toString())
    }

    private fun calculateIngredientsAmountByServings(
        previousServingsAmount: Int,
        newServingsAmount: Int,
        ingredients: List<RecipeIngredient>,
    ): List<RecipeIngredient> {
        val mIngredients = ingredients
        if (previousServingsAmount == newServingsAmount) {
            return mIngredients
        } else {
            mIngredients.forEach { recipeIngredient ->
                recipeIngredient.ingredientMeasure.amount = recipeIngredient.ingredientMeasure.amount * newServingsAmount / previousServingsAmount
            }
            return mIngredients
        }
    }

    fun updateIngredientsAmount(previousServingsAmount: Int, newServingsAmount: Int) {
        val currentIngredients = _ingredients.value ?: emptyList()
        val calculatedIngredients = calculateIngredientsAmountByServings(previousServingsAmount, newServingsAmount, currentIngredients)
        _servings = newServingsAmount
        _ingredients.value = calculatedIngredients
        Timber.tag(TAG).d(_servings.toString())
        Timber.tag(TAG).d(_ingredients.value.toString())
    }

    companion object {
        private const val TAG = "Details ViewModel"
    }
}

