package com.example.foodhelper.model.remote.recipesearch


import com.google.gson.annotations.SerializedName

data class NutritionDto(
    @SerializedName("nutrients")
    val nutrients: List<NutrientDto>
)