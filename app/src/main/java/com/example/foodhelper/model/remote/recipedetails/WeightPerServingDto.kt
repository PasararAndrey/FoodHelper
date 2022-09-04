package com.example.foodhelper.model.remote.recipedetails


import com.google.gson.annotations.SerializedName

data class WeightPerServingDto(
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("unit")
    val unit: String
)