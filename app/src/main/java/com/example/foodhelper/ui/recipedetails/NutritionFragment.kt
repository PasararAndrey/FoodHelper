package com.example.foodhelper.ui.recipedetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.foodhelper.R
import com.example.foodhelper.databinding.FragmentNutritionBinding

class NutritionFragment : Fragment(R.layout.fragment_nutrition) {
    private var _binding: FragmentNutritionBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNutritionBinding.bind(view)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}