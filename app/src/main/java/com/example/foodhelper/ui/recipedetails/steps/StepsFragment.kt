package com.example.foodhelper.ui.recipedetails.steps

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodhelper.R
import com.example.foodhelper.ViewModelFactory
import com.example.foodhelper.databinding.FragmentStepsBinding
import com.example.foodhelper.ui.recipedetails.RecipeDetailsViewModel
import com.example.foodhelper.util.applicationComponent
import javax.inject.Inject

class StepsFragment : Fragment(R.layout.fragment_steps) {
    private var _binding: FragmentStepsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var stepsAdapter: StepsAdapter

    private val mViewModel: RecipeDetailsViewModel by viewModels({ requireActivity() }) { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().applicationComponent.inject(this@StepsFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentStepsBinding.bind(view)
        setupStepsAdapter()
        setupObservingRecipeSteps()
    }

    private fun setupObservingRecipeSteps() {
        mViewModel.steps.observe(viewLifecycleOwner) { newSteps ->
            stepsAdapter.setData(newSteps)
        }
    }

    private fun setupStepsAdapter() {
        binding.rvSteps.apply {
            adapter = stepsAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}