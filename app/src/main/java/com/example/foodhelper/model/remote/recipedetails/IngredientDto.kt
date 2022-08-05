package com.example.foodhelper.model.remote.recipedetails


import com.google.gson.annotations.SerializedName

data class IngredientDto(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("nutrients")
    val nutrients: List<NutrientDto>,
    @SerializedName("unit")
    val unit: String
)