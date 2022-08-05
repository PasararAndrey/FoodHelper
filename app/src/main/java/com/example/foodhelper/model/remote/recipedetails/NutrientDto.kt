package com.example.foodhelper.model.remote.recipedetails


import com.google.gson.annotations.SerializedName

data class NutrientDto(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("percentOfDailyNeeds")
    val percentOfDailyNeeds: Double,
    @SerializedName("unit")
    val unit: String
)