package com.example.foodhelper.data

import android.util.Log
import com.example.foodhelper.data.recipesearch.RecipeSearchDto
import com.example.foodhelper.data.recipesearch.ResultSearchRecipeDto
import com.example.foodhelper.model.RecipePreview
import javax.inject.Inject
import kotlin.math.roundToInt

class RecipeRepository @Inject constructor(
    private val recipesRemoteDataSource: RecipesRemoteDataSource,
) {

    suspend fun searchRecipes(
        query: String,
        cuisines: List<String>? = null,
        diets: List<String>? = null,
        intolerances: List<String>? = null,
    ): List<RecipePreview>? {
        val result = recipesRemoteDataSource.searchRecipe(
            query,
            cuisines?.joinToString(","),
            diets?.joinToString(","),
            intolerances?.joinToString(",")
        )
        Log.d(TAG, "searchRecipes: result: \n ${result.body()}")
        return result.body().toRecipePreviewList()
    }

    companion object {
        private const val TAG = "RecipeRepository"
    }
}

private fun RecipeSearchDto?.toRecipePreviewList(): List<RecipePreview>? {
    return this?.results.toRecipePreviewList()
}


private fun List<ResultSearchRecipeDto>?.toRecipePreviewList(): List<RecipePreview>? {
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
