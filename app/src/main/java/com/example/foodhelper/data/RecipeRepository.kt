package com.example.foodhelper.data

import android.util.Log
import com.example.foodhelper.data.util.ApiResult
import com.example.foodhelper.data.util.onError
import com.example.foodhelper.data.util.onException
import com.example.foodhelper.data.util.onSuccess
import com.example.foodhelper.model.remote.recipesearch.RecipeSearchDto
import com.example.foodhelper.model.remote.recipesearch.ResultSearchRecipeDto
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
        val result: ApiResult<RecipeSearchDto> = recipesRemoteDataSource.searchRecipe(
            query,
            cuisines?.joinToString(","),
            diets?.joinToString(","),
            intolerances?.joinToString(",")
        )
        var list: List<RecipePreview>? = listOf()
        result.onSuccess {
            list = it.toRecipePreviewList()
        }.onError { code, message ->
            Log.d(TAG, "Search Error: $code $message")
        }.onException {
            Log.d(TAG, "Search exception : ${it.printStackTrace()}")
        }


        return list
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
