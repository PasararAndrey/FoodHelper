package com.example.foodhelper.ui.search

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodhelper.R
import com.example.foodhelper.ViewModelFactory
import com.example.foodhelper.databinding.FragmentFilterSearchBinding
import com.example.foodhelper.model.FilterSearchItem
import com.example.foodhelper.util.applicationComponent
import javax.inject.Inject

class FilterSearchFragment : Fragment(R.layout.fragment_filter_search) {
    private var _binding: FragmentFilterSearchBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val mViewModel by viewModels<RecipeSearchViewModel>({ requireActivity() }) { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().applicationComponent.inject(this@FilterSearchFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFilterSearchBinding.bind(view)
        setupSeeAlls()
        setupCuisines()
        setupDiets()
        setupIntolerances()
        setupActions()
    }

    private fun setupActions() {
        binding.btnApply.setOnClickListener {
            val action = FilterSearchFragmentDirections.actionFilterSearchFragmentToRecipeSearchFragment()
            findNavController().navigate(action)
        }

        binding.btnReset.setOnClickListener {
            mViewModel.reset()
        }

    }

    private fun setupSeeAlls() {
        mViewModel.apply {
            isExpandedCuisines.observe(viewLifecycleOwner) { isExpanded ->
                binding.tvSeeAllCuisines.text = if (isExpanded) {
                    resources.getString(R.string.tv_filter_search_hide)
                } else {
                    resources.getString(R.string.tv_filter_search_see_all)
                }
            }
            isExpandedDiets.observe(viewLifecycleOwner) { isExpanded ->
                binding.tvSeeAllDiets.text = if (isExpanded) {
                    resources.getString(R.string.tv_filter_search_hide)
                } else {
                    resources.getString(R.string.tv_filter_search_see_all)
                }
            }
            isExpandedIntolerances.observe(viewLifecycleOwner) { isExpanded ->
                binding.tvSeeAllIntolerances.text = if (isExpanded) {
                    resources.getString(R.string.tv_filter_search_hide)
                } else {
                    resources.getString(R.string.tv_filter_search_see_all)
                }
            }
        }
    }

    private fun setupCuisines() {
        val cuisines: List<FilterSearchItem> = mViewModel.cuisines.value ?: emptyList()
        val mAdapter = FilterSearchAdapter { position, isChecked ->
            mViewModel.updateCuisines(position, isChecked)
        }
        val mLayoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
        mAdapter.setData(cuisines)
        mViewModel.cuisines.observe(viewLifecycleOwner) {
            mAdapter.setData(it)
        }
        binding.apply {
            rvCuisines.adapter = mAdapter
            rvCuisines.layoutManager = mLayoutManager
            tvSeeAllCuisines.setOnClickListener {
                val isExpanded: Boolean = mViewModel.isExpandedCuisines.value ?: false
                mViewModel.resizeCuisines(
                    isAllNeeded = !isExpanded
                )
            }
        }
    }

    private fun setupDiets() {
        val diets: List<FilterSearchItem> = mViewModel.diets.value ?: emptyList()
        val mAdapter = FilterSearchAdapter { position, isChecked ->
            mViewModel.updateDiets(position, isChecked)
        }
        val mLayoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
        mAdapter.setData(diets)
        mViewModel.diets.observe(viewLifecycleOwner) {
            mAdapter.setData(it)
        }
        binding.apply {
            rvDiets.adapter = mAdapter
            rvDiets.layoutManager = mLayoutManager
            tvSeeAllDiets.setOnClickListener {
                val isExpanded: Boolean = mViewModel.isExpandedDiets.value ?: false
                mViewModel.resizeDiets(
                    isAllNeeded = !isExpanded
                )
            }
        }
    }

    private fun setupIntolerances() {
        val intolerances: List<FilterSearchItem> = mViewModel.intolerances.value ?: emptyList()
        val mAdapter = FilterSearchAdapter { position, isChecked ->
            mViewModel.updateIntolerances(position, isChecked)
        }
        val mLayoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
        mAdapter.setData(intolerances)
        mViewModel.intolerances.observe(viewLifecycleOwner) {
            mAdapter.setData(it)
        }
        binding.apply {
            rvIntolerances.adapter = mAdapter
            rvIntolerances.layoutManager = mLayoutManager
            tvSeeAllIntolerances.setOnClickListener {
                val isExpanded: Boolean = mViewModel.isExpandedIntolerances.value ?: false
                mViewModel.resizeIntolerances(
                    isAllNeeded = !isExpanded
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}