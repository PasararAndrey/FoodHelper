package com.example.foodhelper.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodhelper.R
import com.example.foodhelper.databinding.FragmentFilterSearchBinding
import com.example.foodhelper.model.FilterSearchItem
import com.google.android.material.snackbar.Snackbar

class FilterSearchFragment : Fragment(R.layout.fragment_filter_search) {
    private var _binding: FragmentFilterSearchBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFilterSearchBinding.bind(view)
        setupCuisines()
        setupDiets()
        setupIntolerances()
    }

    private fun setupIntolerances() {
        val mAdapter = FilterSearchAdapter { position, isChecked ->
            Snackbar.make(requireContext(), binding.root, "$position $isChecked", Snackbar.LENGTH_SHORT).show()
        }
        val mLayoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)

        val intolerances = listOf(
            FilterSearchItem(true, "Dairy"),
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
        mAdapter.setData(intolerances.subList(0, 3))
        binding.apply {
            rvIntolerances.adapter = mAdapter
            rvIntolerances.layoutManager = mLayoutManager
            tvSeeAllIntolerances.setOnClickListener {
                if (tvSeeAllIntolerances.text == resources.getString(R.string.tv_filter_search_see_all)) {
                    tvSeeAllIntolerances.text = resources.getString(R.string.tv_filter_search_hide)
                    mAdapter.setData(intolerances)
                } else {
                    tvSeeAllIntolerances.text = resources.getString(R.string.tv_filter_search_see_all)
                    mAdapter.setData(intolerances.subList(0, 3))
                }
            }
        }
    }

    private fun setupDiets() {
        val mAdapter = FilterSearchAdapter { position, isChecked ->
            Snackbar.make(requireContext(), binding.root, "$position $isChecked", Snackbar.LENGTH_SHORT).show()
        }
        val mLayoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)

        val diets = listOf(
            FilterSearchItem(true, "Gluten Free"),
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
        mAdapter.setData(diets.subList(0, 3))
        binding.apply {
            rvDiets.adapter = mAdapter
            rvDiets.layoutManager = mLayoutManager
            tvSeeAllDiets.setOnClickListener {
                if (tvSeeAllDiets.text == resources.getString(R.string.tv_filter_search_see_all)) {
                    tvSeeAllDiets.text = resources.getString(R.string.tv_filter_search_hide)
                    mAdapter.setData(diets)
                } else {
                    tvSeeAllDiets.text = resources.getString(R.string.tv_filter_search_see_all)
                    mAdapter.setData(diets.subList(0, 3))
                }
            }
        }
    }

    private fun setupCuisines() {
        val mAdapter = FilterSearchAdapter { position, isChecked ->
            Snackbar.make(requireContext(), binding.root, "$position $isChecked", Snackbar.LENGTH_SHORT).show()
        }
        val mLayoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)

        val cuisines = listOf(
            FilterSearchItem(true, "African"),
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
        mAdapter.setData(cuisines.subList(0, 3))
        binding.apply {
            rvCuisines.adapter = mAdapter
            rvCuisines.layoutManager = mLayoutManager
            tvSeeAllCuisines.setOnClickListener {
                if (tvSeeAllCuisines.text == resources.getString(R.string.tv_filter_search_see_all)) {
                    tvSeeAllCuisines.text = resources.getString(R.string.tv_filter_search_hide)
                    mAdapter.setData(cuisines)
                } else {
                    tvSeeAllCuisines.text = resources.getString(R.string.tv_filter_search_see_all)
                    mAdapter.setData(cuisines.subList(0, 3))
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}