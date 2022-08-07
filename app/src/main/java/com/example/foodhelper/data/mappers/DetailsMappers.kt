package com.example.foodhelper.data.mappers

import com.example.foodhelper.model.remote.*
import com.example.foodhelper.model.remote.recipedetails.*

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
    return RecipeGeneral(this.id, this.title, this.image, this.servings, this.readyInMinutes, this.diets, this.cuisines)
}

private fun RecipeDetailsDto.recipeSteps(): List<RecipeStep> {
    return this.analyzedInstructions.first().steps.map { stepDto ->
        stepDto.toRecipeStep()
    }
}

private fun RecipeDetailsDto.recipeIngredients(): List<RecipeIngredient> {
    return this.extendedIngredients.map { ingredient ->
        RecipeIngredient(ingredient.id,
            ingredient.image,
            ingredient.name,
            ingredient.measures.toIngredientMeasures(),
            getIngredientNutrients(ingredient))
    }
}

private fun RecipeDetailsDto.getIngredientNutrients(ingredient: ExtendedIngredientDto): List<IngredientNutrients>? {
    return findIngredientNutrientsDto(ingredient).toIngredientNutrients()
}


private fun List<NutrientDto>?.toIngredientNutrients(): List<IngredientNutrients>? {
    return this?.map {
        it.toIngredientNutrient()
    }
}

private fun NutrientDto.toIngredientNutrient(): IngredientNutrients {
    return IngredientNutrients(this.name, this.amount, this.unit)
}


private fun RecipeDetailsDto.findIngredientNutrientsDto(ingredient: ExtendedIngredientDto): List<NutrientDto>? {
    return this.nutrition.ingredients.find { ingredientDto: IngredientDto -> ingredientDto.id == ingredient.id }?.nutrients
}


private fun MeasuresDto.toIngredientMeasures(): IngredientMeasures {
    return IngredientMeasures(
        usMeasure = IngredientMeasure(this.us.amount, this.us.unitShort),
        metricMeasure = IngredientMeasure(this.metric.amount, this.metric.unitShort)
    )
}

private fun StepDto.toRecipeStep(): RecipeStep {
    return RecipeStep(this.number, this.step)
}