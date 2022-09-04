package com.example.foodhelper.ui.recipedetails

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.foodhelper.R
import com.example.foodhelper.ViewModelFactory
import com.example.foodhelper.databinding.FragmentRecipeDetailsBinding
import com.example.foodhelper.ui.recipedetails.ingredients.IngredientsFragment
import com.example.foodhelper.ui.recipedetails.overview.OverviewFragment
import com.example.foodhelper.ui.recipedetails.steps.StepsFragment
import com.example.foodhelper.util.applicationComponent
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class RecipeDetailsFragment : Fragment(R.layout.fragment_recipe_details) {
    private var _binding: FragmentRecipeDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: RecipeDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val mViewModel: RecipeDetailsViewModel by viewModels({ requireActivity() }) { viewModelFactory }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().applicationComponent.inject(this@RecipeDetailsFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRecipeDetailsBinding.bind(view)
        binding.vpPages.adapter = DetailsPagerAdapter(this)
        mViewModel.getRecipe(args.recipeId)
        TabLayoutMediator(binding.tlTabs, binding.vpPages) { tab, position ->
            when (position) {
                0 -> tab.text = "Overview"
                1 -> tab.text = "Elements"
                else -> tab.text = "Cooking"
            }
        }.attach()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class DetailsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OverviewFragment()
            1 -> IngredientsFragment()
            else -> StepsFragment()
        }
    }
}
