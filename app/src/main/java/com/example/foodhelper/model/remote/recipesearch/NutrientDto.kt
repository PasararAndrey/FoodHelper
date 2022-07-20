package com.example.foodhelper.model.remote.recipesearch


import com.google.gson.annotations.SerializedName

data class NutrientDto(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("unit")
    val unit: String
)