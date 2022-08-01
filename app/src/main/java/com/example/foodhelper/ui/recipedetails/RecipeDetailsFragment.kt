package com.example.foodhelper.ui.recipedetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.foodhelper.R
import com.example.foodhelper.databinding.FragmentRecipeDetailsBinding
import com.google.android.material.tabs.TabLayoutMediator

class RecipeDetailsFragment : Fragment(R.layout.fragment_recipe_details) {
    private var _binding: FragmentRecipeDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRecipeDetailsBinding.bind(view)
        binding.vpPages.adapter = DetailsPagerAdapter(this)
        TabLayoutMediator(binding.tlTabs, binding.vpPages) { tab, position ->
            when (position) {
                0 -> tab.text = "Overview"
                1 -> tab.text = "Elements"
                2 -> tab.text = "Cooking"
                else -> tab.text = "Nutrition"
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
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OverviewFragment()
            1 -> IngredientsFragment()
            2 -> StepsFragment()
            else -> NutritionFragment()
        }
    }

}
