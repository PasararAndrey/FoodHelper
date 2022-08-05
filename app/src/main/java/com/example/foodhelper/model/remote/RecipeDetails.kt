package com.example.foodhelper.model.remote

import com.google.gson.annotations.SerializedName


data class RecipeDetails(
    val general: RecipeGeneral,
    val ingredients: List<RecipeIngredient>,
    val steps: List<RecipeStep>,
    val nutrition: RecipeNutrition,
)

data class RecipeGeneral(
    val recipeId: Int,
    val title: String,
    val image: String,
    val servings: Int,
    val readyInMinutes: Int,
    val diets: List<String>,
    val cuisines: List<String>,
)


data class RecipeIngredient(
    val ingredientId: Int,
    val image: String,
    val name: String,
    val ingredientMeasures: IngredientMeasures,
    val nutrients: List<IngredientNutrients>,
)

data class IngredientNutrients(
    val nutrientName: String,
    val amount: Double,
    val unit: String,
)


data class IngredientMeasures(
    val usMeasure: IngredientMeasure,
    val metricMeasure: IngredientMeasure,
)

data class IngredientMeasure(
    val amount: Double,
    val unitLong: String,
    val unitShort: String,
)

data class RecipeStep(
    val number: Int,
    val step: String,
)


data class RecipeNutrition(
    val amount: Double,
    val name: String,
    val unit: String,
)

