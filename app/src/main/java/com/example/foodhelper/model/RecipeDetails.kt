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
) {
    override fun toString(): String {
        return """RecipeGeneral(
            recipeId = $recipeId
            title = $title
            image = $image
            servings = $servings
            readyInMinutes = $readyInMinutes
            diets = $diets
            cuisines = $cuisines
            )
        """
    }
}


data class RecipeIngredientWithMeasures(
    val ingredientId: Int,
    val image: String?,
    val name: String,
    val ingredientMeasures: IngredientMeasures,
    val nutrients: List<IngredientNutrients>?,
) {
    override fun toString(): String {
        return """
            RecipeGeneral(
            ingredientId = $ingredientId
            name = $name
            image = $image
            ingredientMeasures = $ingredientMeasures
            nutrients = $nutrients
            )
        """
    }

    fun toStringMeasure(): String {
        return """
            RecipeGeneral(
            ingredientId = $ingredientId
            ingredientMeasures = $ingredientMeasures
            )
        """
    }
}


data class RecipeIngredient(
    val ingredientId: Int,
    val image: String?,
    val name: String,
    val ingredientMeasure: IngredientMeasure,
    val nutrients: List<IngredientNutrients>?,
) {
    override fun toString(): String {
        return """
            RecipeIngredient(
            recipeId = $ingredientId
            image = $image
            name = $name
            ingredientMeasure = $ingredientMeasure
            nutrients = $nutrients
            )
        """
    }

}


data class IngredientNutrients(
    val nutrientName: String,
    val amount: Float,
    val unit: String,
)


data class IngredientMeasures(
    val usMeasure: IngredientMeasure,
    val metricMeasure: IngredientMeasure,
)

data class IngredientMeasure(
    var amount: Float,
    val unitShort: String,
)

data class RecipeStep(
    val number: Int,
    val step: String,
)


data class RecipeNutrition(
    val amount: Float,
    val name: String,
    val unit: String,
)

