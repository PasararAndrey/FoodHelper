package com.example.foodhelper.model.remote.recipedetails


import com.google.gson.annotations.SerializedName

data class IngredientsDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("localizedName")
    val localizedName: String,
    @SerializedName("name")
    val name: String
)