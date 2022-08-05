package com.example.foodhelper.model.remote.recipedetails


import com.google.gson.annotations.SerializedName

data class NutritionsDto(
    @SerializedName("caloricBreakdown")
    val caloricBreakdown: CaloricBreakdownDto,
    @SerializedName("flavonoids")
    val flavonoids: List<FlavonoidDto>,
    @SerializedName("ingredients")
    val ingredients: List<IngredientDto>,
    @SerializedName("nutrients")
    val nutrients: List<NutrientDto>,
    @SerializedName("properties")
    val properties: List<PropertyDto>,
    @SerializedName("weightPerServing")
    val weightPerServing: WeightPerServingDto
)