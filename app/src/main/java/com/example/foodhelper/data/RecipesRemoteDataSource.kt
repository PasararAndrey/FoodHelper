package com.example.foodhelper.data

import com.example.foodhelper.data.recipesearch.RecipeSearchDto
import com.example.foodhelper.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
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
    ): Response<RecipeSearchDto> {
        return withContext(ioDispatcher) {
            recipesApi.searchRecipes(
                searchQuery = query,
                cuisines = cuisines,
                diets = diets,
                intolerances = intolerances
            )
        }
    }

    companion object {
        private const val TAG = "RecipesRemoteDataSource"
    }
}