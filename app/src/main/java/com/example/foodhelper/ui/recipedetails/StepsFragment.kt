package com.example.foodhelper.ui.recipedetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.foodhelper.R
import com.example.foodhelper.databinding.FragmentStepsBinding

class StepsFragment : Fragment(R.layout.fragment_steps) {
    private var _binding: FragmentStepsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentStepsBinding.bind(view)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}