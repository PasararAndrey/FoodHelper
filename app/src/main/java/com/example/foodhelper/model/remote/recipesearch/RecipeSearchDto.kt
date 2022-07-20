package com.example.foodhelper.model.remote.recipesearch


import com.google.gson.annotations.SerializedName

data class RecipeSearchDto(
    @SerializedName("number")
    val number: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    val results: List<ResultSearchRecipeDto>,
    @SerializedName("totalResults")
    val totalResults: Int
)