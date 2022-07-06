package com.example.foodhelper.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.foodhelper.R
import com.example.foodhelper.databinding.FragmentRecipeSearchBinding

class RecipeSearchFragment: Fragment(R.layout.fragment_recipe_search) {
    private var _binding: FragmentRecipeSearchBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRecipeSearchBinding.bind(view)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}