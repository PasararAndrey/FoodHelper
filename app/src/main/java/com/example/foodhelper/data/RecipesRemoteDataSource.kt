package com.example.foodhelper.data

import com.example.foodhelper.data.recipe.remote.RecipeAPi
import com.example.foodhelper.data.util.ApiResult
import com.example.foodhelper.model.remote.recipesearch.RecipeSearchDto
import com.example.foodhelper.di.IoDispatcher
import com.example.foodhelper.model.remote.recipedetails.RecipeDetailsDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipesRemoteDataSource @Inject constructor(
    private val recipesApi: RecipeAPi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {

    suspend fun searchRecipe(
        query: String,
        cuisines: String? = null,
        diets: String? = null,
        intolerances: String? = null,
    ): ApiResult<RecipeSearchDto> {
        return withContext(ioDispatcher) {
            recipesApi.searchRecipes(query, cuisines, diets, intolerances)
        }
    }

    suspend fun getRecipeDetails(recipeId: Int):ApiResult<RecipeDetailsDto> {
        return withContext(ioDispatcher) {
            recipesApi.getRecipeDetails(recipeId)
        }
    }

    companion object {
        private const val TAG = "RecipesRemoteDataSource"
    }
}