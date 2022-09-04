package com.example.foodhelper.model.remote.recipedetails


import com.google.gson.annotations.SerializedName

data class WinePairingDto(
    @SerializedName("pairedWines")
    val pairedWines: List<String>,
    @SerializedName("pairingText")
    val pairingText: String,
    @SerializedName("productMatches")
    val productMatches: List<ProductMatcheDto>
)