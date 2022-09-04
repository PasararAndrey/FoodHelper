package com.example.foodhelper.util

import com.example.foodhelper.model.*

private fun RecipeIngredientWithMeasures.getRecipeIngredientByMeasure(measure: Measure): RecipeIngredient {
    return RecipeIngredient(
        this.ingredientId,
        this.image,
        this.name,
        this.ingredientMeasures.getMeasure(measure).copy()
    )
}

private fun IngredientMeasures.getMeasure(measure: Measure): IngredientMeasure {
    when (measure) {
        Measure.US -> return this.usMeasure
        Measure.METRIC -> return this.metricMeasure
    }
}

fun List<RecipeIngredientWithMeasures>.getRecipeIngredientsByMeasure(measure: Measure): List<RecipeIngredient> {
    return this.map { ingredientWithMeasures ->
        ingredientWithMeasures.getRecipeIngredientByMeasure(measure)
    }
}
