package com.example.foodhelper.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodhelper.data.RecipeRepository
import com.example.foodhelper.model.FilterSearchItem
import com.example.foodhelper.model.RecipePreview
import kotlinx.coroutines.launch
import org.w3c.dom.ls.LSException
import javax.inject.Inject

class RecipeSearchViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
) : ViewModel() {
    private var _isExpandedCuisines = MutableLiveData(false)
    val isExpandedCuisines: LiveData<Boolean> get() = _isExpandedCuisines
    private var _isExpandedDiets = MutableLiveData(false)
    val isExpandedDiets: LiveData<Boolean> get() = _isExpandedDiets
    private var _isExpandedIntolerances = MutableLiveData(false)
    val isExpandedIntolerances: LiveData<Boolean> get() = _isExpandedIntolerances

    private var _recipes = MutableLiveData<List<RecipePreview>>(emptyList())
    val recipes: LiveData<List<RecipePreview>> get() = _recipes

    private val cuisinesList = mutableListOf(
        FilterSearchItem(false, "African"),
        FilterSearchItem(false, "American"),
        FilterSearchItem(false, "British"),
        FilterSearchItem(false, "Cajun"),
        FilterSearchItem(false, "Caribbean"),
        FilterSearchItem(false, "Chinese"),
        FilterSearchItem(false, "Eastern European"),
        FilterSearchItem(false, "European"),
        FilterSearchItem(false, "French"),
        FilterSearchItem(false, "German"),
        FilterSearchItem(false, "Greek"),
        FilterSearchItem(false, "Indian"),
        FilterSearchItem(false, "Irish"),
        FilterSearchItem(false, "Italian"),
        FilterSearchItem(false, "Japanese"),
        FilterSearchItem(false, "Jewish"),
        FilterSearchItem(false, "Korean"),
        FilterSearchItem(false, "Latin American"),
        FilterSearchItem(false, "Mediterranean"),
        FilterSearchItem(false, "Mexican"),
        FilterSearchItem(false, "Middle Eastern"),
        FilterSearchItem(false, "Nordic"),
        FilterSearchItem(false, "Southern"),
        FilterSearchItem(false, "Spanish"),
        FilterSearchItem(false, "Thai"),
        FilterSearchItem(false, "Vietnamese")
    )
    private var _cuisines = MutableLiveData(cuisinesList.subList(0, 3).toList())
    val cuisines: LiveData<List<FilterSearchItem>> get() = _cuisines

    private val dietsList = mutableListOf(
        FilterSearchItem(false, "Gluten Free"),
        FilterSearchItem(false, "Ketogenic"),
        FilterSearchItem(false, "Vegetarian"),
        FilterSearchItem(false, "Lacto-Vegetarian"),
        FilterSearchItem(false, "Ovo-Vegetarian"),
        FilterSearchItem(false, "Vegan"),
        FilterSearchItem(false, "Pescetarian"),
        FilterSearchItem(false, "Paleo"),
        FilterSearchItem(false, "Primal"),
        FilterSearchItem(false, "Low FODMAP"),
        FilterSearchItem(false, "Whole30"),
    )
    private var _diets = MutableLiveData(dietsList.subList(0, 3).toList())
    val diets: LiveData<List<FilterSearchItem>> get() = _diets


    private val intolerancesList = mutableListOf(
        FilterSearchItem(false, "Dairy"),
        FilterSearchItem(false, "Egg"),
        FilterSearchItem(false, "Gluten"),
        FilterSearchItem(false, "Grain"),
        FilterSearchItem(false, "Peanut"),
        FilterSearchItem(false, "Seafood"),
        FilterSearchItem(false, "Sesame"),
        FilterSearchItem(false, "Shellfish"),
        FilterSearchItem(false, "Soy"),
        FilterSearchItem(false, "Sulfite"),
        FilterSearchItem(false, "Tree Nut"),
        FilterSearchItem(false, "Wheat"),
    )
    private var _intolerances = MutableLiveData(intolerancesList.subList(0, 3).toList())
    val intolerances: LiveData<List<FilterSearchItem>> get() = _intolerances

    fun updateCuisines(position: Int, isChecked: Boolean) {
        val cuisines = _cuisines.value!!.toMutableList()
        cuisines[position] = cuisines[position].copy(isChecked = !isChecked)
        cuisinesList[position] = cuisines[position]
        _cuisines.value = cuisines

    }

    fun updateDiets(position: Int, isChecked: Boolean) {
        val diets = _diets.value!!.toMutableList()
        diets[position] = diets[position].copy(isChecked = !isChecked)
        dietsList[position] = diets[position]
        _diets.value = diets

    }

    fun updateIntolerances(position: Int, isChecked: Boolean) {
        val intolerances = _intolerances.value!!.toMutableList()
        intolerances[position] = intolerances[position].copy(isChecked = !isChecked)
        intolerancesList[position] = intolerances[position]
        _intolerances.value = intolerances

    }

    fun resizeCuisines(isAllNeeded: Boolean) {
        _isExpandedCuisines.value = isAllNeeded
        if (isAllNeeded) {
            _cuisines.value = cuisinesList
        } else {
            _cuisines.value = cuisinesList.subList(0, 3)
        }
    }

    fun resizeDiets(isAllNeeded: Boolean) {
        _isExpandedDiets.value = isAllNeeded
        if (isAllNeeded) {
            _diets.value = dietsList
        } else {
            _diets.value = dietsList.subList(0, 3)
        }
    }

    fun resizeIntolerances(isAllNeeded: Boolean) {
        _isExpandedIntolerances.value = isAllNeeded
        if (isAllNeeded) {
            _intolerances.value = intolerancesList
        } else {
            _intolerances.value = intolerancesList.subList(0, 3)
        }
    }

    fun reset() {
        cuisinesList.forEachIndexed { index, _ ->
            cuisinesList[index] = cuisinesList[index].copy(isChecked = false)
        }
        dietsList.forEachIndexed { index, _ ->
            dietsList[index] = dietsList[index].copy(isChecked = false)
        }
        intolerancesList.forEachIndexed { index, _ ->
            intolerancesList[index] = intolerancesList[index].copy(isChecked = false)
        }

        resizeCuisines(_isExpandedCuisines.value ?: false)
        resizeDiets(_isExpandedDiets.value ?: false)
        resizeIntolerances(_isExpandedIntolerances.value ?: false)
    }

    fun searchRecipes(query: String) {
        val cuisines: List<String> = cuisinesList.filter {
            it.isChecked
        }.map {
            it.itemText
        }
        val diets: List<String> = dietsList.filter {
            it.isChecked
        }.map {
            it.itemText
        }
        val intolerances: List<String> = intolerancesList.filter {
            it.isChecked
        }.map {
            it.itemText
        }
        viewModelScope.launch {
            val searchResult: List<RecipePreview>? = recipeRepository.searchRecipes(
                query = query,
                cuisines = cuisines,
                diets = diets,
                intolerances = intolerances
            )
            _recipes.value = searchResult ?: emptyList()
        }
    }


}
