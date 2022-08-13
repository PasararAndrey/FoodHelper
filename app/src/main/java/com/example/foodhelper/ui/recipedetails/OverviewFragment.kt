package com.example.foodhelper.ui.recipedetails

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodhelper.R
import com.example.foodhelper.ViewModelFactory
import com.example.foodhelper.databinding.FragmentOverviewBinding
import com.example.foodhelper.util.applicationComponent
import com.squareup.picasso.Picasso
import javax.inject.Inject

class OverviewFragment : Fragment(R.layout.fragment_overview) {
    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var dietsAdapter: OverviewAdapter

    @Inject
    lateinit var cuisinesAdapter: OverviewAdapter


    private val mViewModel: RecipeDetailsViewModel by viewModels({ requireActivity() }) { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().applicationComponent.inject(this@OverviewFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOverviewBinding.bind(view)
        setupObservingGeneralRecipeData()
        setupCuisinesList()
        setupDietsList()
    }

    private fun setupObservingGeneralRecipeData() {
        mViewModel.currentOverview.observe(viewLifecycleOwner) { recipeGeneral ->
            binding.apply {
                dietsAdapter.setData(recipeGeneral.diets)
                cuisinesAdapter.setData(recipeGeneral.cuisines)
                tvRecipeTitle.text = recipeGeneral.title
                tvRecipeServings.text = resources.getString(R.string.tv_overview_serves, recipeGeneral.servings)
                tvRecipeReadyInMinutes.text = resources.getString(R.string.tv_overview_ready_minutes, recipeGeneral.readyInMinutes)
                Picasso.get().load(recipeGeneral.image).into(ivRecipeImage)

            }
        }
    }

    private fun setupDietsList() {
        binding.rvRecipeDiets.apply {
            adapter = dietsAdapter
            layoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
        }
    }

    private fun setupCuisinesList() {
        binding.rvRecipeCuisines.apply {
            adapter = cuisinesAdapter
            layoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}