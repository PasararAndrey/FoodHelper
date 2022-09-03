package com.example.foodhelper.data.mappers

import com.example.foodhelper.model.*
import com.example.foodhelper.model.remote.recipedetails.MeasuresDto
import com.example.foodhelper.model.remote.recipedetails.RecipeDetailsDto
import com.example.foodhelper.model.remote.recipedetails.StepDto

fun RecipeDetailsDto.toRecipeDetails(): RecipeDetails {
    return RecipeDetails(
        recipeGeneral(),
        recipeIngredients(),
        recipeSteps(),
        recipeNutritions()
    )
}

private fun RecipeDetailsDto.recipeNutritions(): List<RecipeNutrition> {
    return this.nutrition.nutrients.map { nutrientDto ->
        RecipeNutrition(nutrientDto.amount, nutrientDto.name, nutrientDto.unit)
    }
}

private fun RecipeDetailsDto.recipeGeneral(): RecipeGeneral {
    return RecipeGeneral(
        recipeId = this.id,
        title = this.title,
        image = this.image,
        servings = this.servings,
        readyInMinutes = this.readyInMinutes,
        diets = this.diets,
        cuisines = this.cuisines,
        fats = this.nutrition.nutrients.find { nutrient -> nutrient.name == "Fat" }?.amount ?: 0F,
        calories = this.nutrition.nutrients.find { nutrient -> nutrient.name == "Calories" }?.amount ?: 0F
    )
}

private fun RecipeDetailsDto.recipeSteps(): List<RecipeStep> {
    if (this.analyzedInstructions.isNotEmpty()) {
        return this.analyzedInstructions.first().steps.map { stepDto ->
            stepDto.toRecipeStep()
        }
    } else {
        return emptyList()
    }
}

private fun RecipeDetailsDto.recipeIngredients(): List<RecipeIngredientWithMeasures> {
    return this.extendedIngredients.map { ingredient ->
        RecipeIngredientWithMeasures(ingredient.id,
            "https://spoonacular.com/cdn/ingredients_100x100/${ingredient.image?.trim()}",
            ingredient.name,
            ingredient.measures.toIngredientMeasures()
        )
    }
}

private fun MeasuresDto.toIngredientMeasures(): IngredientMeasures {
    return IngredientMeasures(
        usMeasure = IngredientMeasure(this.us.amount, this.us.unitShort ?: ""),
        metricMeasure = IngredientMeasure(this.metric.amount, this.metric.unitShort ?: "")
    )
}

private fun StepDto.toRecipeStep(): RecipeStep {
    return RecipeStep(this.number, this.step)
}

