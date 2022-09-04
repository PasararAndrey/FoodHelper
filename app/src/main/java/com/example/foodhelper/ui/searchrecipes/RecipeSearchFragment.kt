package com.example.foodhelper.ui.searchrecipes

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodhelper.R
import com.example.foodhelper.ViewModelFactory
import com.example.foodhelper.databinding.FragmentRecipeSearchBinding
import com.example.foodhelper.util.applicationComponent
import javax.inject.Inject

class RecipeSearchFragment : Fragment(R.layout.fragment_recipe_search) {
    private var _binding: FragmentRecipeSearchBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var mAdapter: SearchRecipeAdapter

    private val mViewModel by viewModels<RecipeSearchViewModel>({ requireActivity() }) { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().applicationComponent.inject(this@RecipeSearchFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRecipeSearchBinding.bind(view)
        setupNavigationToDetailsScreen()
        setupNavigationToFilterScreen()
        setupRecipeSearch()
        setupRecipesList()
        setupRecipesRecycler()
    }

    private fun setupRecipesList() {
        mViewModel.recipes.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                mAdapter.submitList(emptyList())
                binding.layoutEmptySearch.root.visibility = View.VISIBLE
                binding.rvRecipesList.visibility = View.GONE
            } else {
                mAdapter.submitList(it)
                binding.layoutEmptySearch.root.visibility = View.GONE
                binding.rvRecipesList.visibility = View.VISIBLE
            }

        }
    }

    private fun setupRecipesRecycler() {
        binding.rvRecipesList.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setupRecipeSearch() {
        binding.svSearchRecipe.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    mViewModel.searchRecipes(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun setupNavigationToDetailsScreen() {
        mAdapter.setOnItemClickListener { recipeId: Int ->
            val action = RecipeSearchFragmentDirections.actionRecipeSearchFragmentToRecipeDetailsFragment(recipeId = recipeId)
            findNavController().navigate(action)
        }
    }

    private fun setupNavigationToFilterScreen() {
        binding.ibFilter.setOnClickListener {
            val action = RecipeSearchFragmentDirections.actionRecipeSearchFragmentToFilterSearchFragment()
            findNavController().navigate(action)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}