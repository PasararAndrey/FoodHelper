package com.example.foodhelper.data.recipesearch


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