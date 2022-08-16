package com.example.foodhelper.ui.recipedetails.ingredients

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.foodhelper.R
import com.example.foodhelper.ViewModelFactory
import com.example.foodhelper.databinding.FragmentIngredientsBinding
import com.example.foodhelper.model.Measure
import com.example.foodhelper.ui.recipedetails.RecipeDetailsViewModel
import com.example.foodhelper.util.applicationComponent
import timber.log.Timber
import javax.inject.Inject

class IngredientsFragment : Fragment(R.layout.fragment_ingredients) {
    private var _binding: FragmentIngredientsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

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
    }

    private fun setUpMeasuresInitialState() {
        binding.rbMetric.isChecked = true
    }

    private fun setUpServingListener() {
        binding.etServings.doAfterTextChanged { servings ->
            if (servings.toString().isNotEmpty()) {
                try {
                    mViewModel.updateIngredientsAmount(mViewModel.servings, servings.toString().toInt())
                } catch (e: NumberFormatException) {
                    Timber.tag(TAG).e("Input number too long: ${e.printStackTrace()}")
                    mViewModel.updateIngredientsAmount(mViewModel.servings, 1)
                }
            }
        }
    }

    private fun setUpServingsInitialState() {
        binding.etServings.setText(mViewModel.servings.toString())
    }

    private fun setUpOnMeasuresClick() {
        binding.rgMeasures.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_metric -> {
                    mViewModel.updateMeasureState(Measure.METRIC)
                }
                R.id.rb_us -> {
                    mViewModel.updateMeasureState(Measure.US)
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