package com.example.foodhelper.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.foodhelper.R
import com.example.foodhelper.databinding.FragmentFilterSearchBinding

class FilterSearchFragment : Fragment(R.layout.fragment_filter_search) {
    private var _binding: FragmentFilterSearchBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFilterSearchBinding.bind(view)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}