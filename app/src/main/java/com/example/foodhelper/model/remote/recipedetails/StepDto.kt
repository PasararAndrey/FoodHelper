package com.example.foodhelper.model.remote.recipedetails


import com.google.gson.annotations.SerializedName

data class StepDto(
    @SerializedName("equipment")
    val equipment: List<EquipmentDto>,
    @SerializedName("ingredients")
    val ingredients: List<IngredientsDto>,
    @SerializedName("number")
    val number: Int,
    @SerializedName("step")
    val step: String
)