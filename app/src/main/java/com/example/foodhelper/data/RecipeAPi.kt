package com.example.foodhelper.data

import com.example.foodhelper.data.util.ApiResult
import com.example.foodhelper.model.remote.recipesearch.RecipeSearchDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RecipeAPi {

    @Headers(
        "x-api-key: ac41522ed3314214b1f8b49485272d42"
    )
    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("query") searchQuery: String,
        @Query("cuisine") cuisines: String? = null,
        @Query("diet") diets: String? = null,
        @Query("intolerances") intolerances: String? = null,
        @Query("number") amount: Int = 5,
        @Query("minProtein") proteins: Int = 10,
        @Query("minCalories") calories: Int = 50,
    ): ApiResult<RecipeSearchDto>

}
