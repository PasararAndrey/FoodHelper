package com.example.foodhelper.ui.recipedetails.ingredients

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodhelper.R
import com.example.foodhelper.ViewModelFactory
import com.example.foodhelper.databinding.FragmentIngredientsBinding
import com.example.foodhelper.model.Measure
import com.example.foodhelper.model.RecipeIngredient
import com.example.foodhelper.ui.recipedetails.RecipeDetailsViewModel
import com.example.foodhelper.util.applicationComponent
import timber.log.Timber
import javax.inject.Inject

class IngredientsFragment : Fragment(R.layout.fragment_ingredients) {
    private var _binding: FragmentIngredientsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var ingredientsAdapter: IngredientsAdapter

    private val mViewModel by viewModels<RecipeDetailsViewModel>({ requireActivity() }) { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().applicationComponent.inject(this@IngredientsFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentIngredientsBinding.bind(view)
        setUpServingsInitialState()
        setUpMeasuresInitialState()
        setUpServingListener()
        setUpOnMeasuresClick()
        setupIngredientsAdapter()
        setupObservingIngredients()
    }

    private fun setupObservingIngredients() {
        mViewModel.ingredients.observe(viewLifecycleOwner) { newIngredients: List<RecipeIngredient> ->
            ingredientsAdapter.submitList(newIngredients)
        }
    }

    private fun setupIngredientsAdapter() {
        binding.rvIngredients.apply {
            adapter = ingredientsAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setUpMeasuresInitialState() {
        binding.rgMeasures.check(R.id.rb_metric)
        mViewModel.updateMeasure(Measure.METRIC)
    }

    private fun setUpServingListener() {
        binding.etServings.doAfterTextChanged { servings ->
            if (servings.toString().isNotEmpty()) {
                try {
                    Timber.tag(TAG).d("New servings amount: ${servings.toString()}")
                    mViewModel.updateServings(servings.toString().toInt())
                } catch (e: NumberFormatException) {
                    Timber.tag(TAG).e("Input number too long: ${e.printStackTrace()}")
                    mViewModel.updateServings(1)
                }
            }
        }
    }

    private fun setUpServingsInitialState() {
        val servings = mViewModel.overview.value?.servings ?: kotlin.run {
            Timber.tag(TAG).d("Can't setup initial state.No servings provided")
            return
        }
        binding.etServings.setText(servings.toString())
    }

    private fun setUpOnMeasuresClick() {
        binding.rgMeasures.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_metric -> {
                    mViewModel.updateMeasure(Measure.METRIC)
                }
                R.id.rb_us -> {
                    mViewModel.updateMeasure(Measure.US)
                }
                else -> {
                    Timber.tag(TAG).e("No such measure supported")
                    return@setOnCheckedChangeListener
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "IngredientsFragment"
    }
}