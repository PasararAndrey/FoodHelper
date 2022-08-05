package com.example.foodhelper.model.remote.recipedetails


import com.google.gson.annotations.SerializedName

data class UsDto(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("unitLong")
    val unitLong: String,
    @SerializedName("unitShort")
    val unitShort: String
)