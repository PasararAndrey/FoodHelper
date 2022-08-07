package com.example.foodhelper.data.mappers

import com.example.foodhelper.model.RecipePreview
import com.example.foodhelper.model.remote.recipesearch.RecipeSearchDto
import com.example.foodhelper.model.remote.recipesearch.ResultSearchRecipeDto
import kotlin.math.roundToInt

fun RecipeSearchDto?.toRecipePreviewList(): List<RecipePreview>? {
    return this?.results.toRecipePreviewList()
}


fun List<ResultSearchRecipeDto>?.toRecipePreviewList(): List<RecipePreview>? {
    return this?.map { searchDto ->
        val calories: String = searchDto.nutrition.nutrients.find { nutrient -> nutrient.name == "Calories" }.let { nutrient ->
            nutrient?.amount?.roundToInt().toString().plus(" ").plus(nutrient?.unit.toString())
        }

        val protein: String = searchDto.nutrition.nutrients.find { nutrient -> nutrient.name == "Protein" }.let { nutrient ->
            nutrient?.amount?.roundToInt().toString().plus(" ").plus(nutrient?.unit.toString())
        }
        RecipePreview(
            id = searchDto.id,
            title = searchDto.title,
            previewImage = searchDto.image,
            calories = calories,
            protein = protein
        )
    }
}
