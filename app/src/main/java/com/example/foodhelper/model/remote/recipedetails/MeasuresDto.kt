package com.example.foodhelper.model.remote.recipedetails


import com.google.gson.annotations.SerializedName

data class MeasuresDto(
    @SerializedName("metric")
    val metric: MetricDto,
    @SerializedName("us")
    val us: UsDto
)