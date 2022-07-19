package com.example.foodhelper.data.recipesearch


import com.google.gson.annotations.SerializedName

data class ResultSearchRecipeDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("imageType")
    val imageType: String,
    @SerializedName("nutrition")
    val nutrition: NutritionDto,
    @SerializedName("title")
    val title: String
)