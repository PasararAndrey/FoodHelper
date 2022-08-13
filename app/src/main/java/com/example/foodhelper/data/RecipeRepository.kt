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
import com.example.foodhelper.model.RecipeDetails
import com.example.foodhelper.model.remote.recipedetails.RecipeDetailsDto
import timber.log.Timber
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
            Timber.tag(TAG).d("Search Error: $code $message")
        }.onException {
            Timber.tag(TAG).d("Search Exception : ${it.message} ${it.printStackTrace()}")
        }
        return list
    }

    suspend fun getRecipeDetails(recipeId: Int): RecipeDetails? {
        val result: ApiResult<RecipeDetailsDto> = recipesRemoteDataSource.getRecipeDetails(recipeId)
        var recipeDetails: RecipeDetails? = null
        result.onSuccess { recipeDetailsDto ->
            recipeDetails = recipeDetailsDto.toRecipeDetails()
        }.onError { code, message ->
            Timber.tag(TAG).d("Details Error: $code $message")
        }.onException {
            Timber.tag(TAG).d("Details Exception : ${it.message} ${it.printStackTrace()}")
        }
        return recipeDetails
    }

    companion object {
        private const val TAG = "RecipeRepository"
    }
}

