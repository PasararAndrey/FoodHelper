package com.example.foodhelper.model.remote.recipedetails


import com.google.gson.annotations.SerializedName

data class FlavonoidDto(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("unit")
    val unit: String
)