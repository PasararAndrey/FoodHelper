package com.example.foodhelper.data

import android.util.Log
import com.example.foodhelper.data.mappers.toRecipeDetails
import com.example.foodhelper.data.mappers.toRecipePreviewList
import com.example.foodhelper.data.util.ApiResult
import com.example.foodhelper.data.util.onError
import com.example.foodhelper.data.util.onException
import com.example.foodhelper.data.util.onSuccess
import com.example.foodhelper.model.remote.recipesearch.RecipeSearchDto
import com.example.foodhelper.model.RecipePreview
import com.example.foodhelper.model.remote.RecipeDetails
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val recipesRemoteDataSource: RecipesRemoteDataSource,
) {

    suspend fun searchRecipes(
        query: String,
        cuisines: List<String>? = null,
        diets: List<String>? = null,
        intolerances: List<String>? = null,
    ): List<RecipePreview>? {
        val result: ApiResult<RecipeSearchDto> = recipesRemoteDataSource
            .searchRecipe(query, cuisines?.joinToString(","), diets?.joinToString(","), intolerances?.joinToString(","))
        var list: List<RecipePreview>? = listOf()
        result.onSuccess {
            list = it.toRecipePreviewList()
        }.onError { code, message ->
            Log.d(TAG, "Search Error: $code $message")
        }.onException {
            Log.d(TAG, "Search Exception : ${it.printStackTrace()}")
        }
        return list
    }

    suspend fun getRecipeDetails(recipeId: Int): List<RecipeDetails> {
        val result = recipesRemoteDataSource.getRecipeDetails(recipeId)
        val recipeDetails = mutableListOf<RecipeDetails>()
        result.onSuccess { recipeDetailsDto ->
            recipeDetails.add(recipeDetailsDto.toRecipeDetails())
        }.onError { code, message ->
            Log.d(TAG, "Details Error: $code $message")
        }.onException {
            Log.d(TAG, "Details Exception : ${it.printStackTrace()}")
        }
        return recipeDetails.toList()
    }

    companion object {
        private const val TAG = "RecipeRepository"
    }
}

