package com.example.foodhelper.model.remote.recipedetails


import com.google.gson.annotations.SerializedName

data class CaloricBreakdownDto(
    @SerializedName("percentCarbs")
    val percentCarbs: Double,
    @SerializedName("percentFat")
    val percentFat: Double,
    @SerializedName("percentProtein")
    val percentProtein: Double
)