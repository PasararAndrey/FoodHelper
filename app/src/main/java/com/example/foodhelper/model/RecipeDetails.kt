package com.example.foodhelper.model


data class RecipeDetails(
    val general: RecipeGeneral,
    val ingredients: List<RecipeIngredientWithMeasures>,
    val steps: List<RecipeStep>,
    val nutritions: List<RecipeNutrition>,
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


data class RecipeIngredientWithMeasures(
    val ingredientId: Int,
    val image: String?,
    val name: String,
    val ingredientMeasures: IngredientMeasures,
    val nutrients: List<IngredientNutrients>?,
)

data class RecipeIngredient(
    val ingredientId: Int,
    val image: String?,
    val name: String,
    val ingredientMeasure: IngredientMeasure,
    val nutrients: List<IngredientNutrients>?,
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
    var amount: Double,
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

