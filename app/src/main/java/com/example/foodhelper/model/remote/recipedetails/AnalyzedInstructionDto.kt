package com.example.foodhelper.model.remote.recipedetails


import com.google.gson.annotations.SerializedName

data class AnalyzedInstructionDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("steps")
    val steps: List<StepDto>
)