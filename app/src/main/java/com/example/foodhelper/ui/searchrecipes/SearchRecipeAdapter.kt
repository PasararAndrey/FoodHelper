package com.example.foodhelper.ui.searchrecipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodhelper.R
import com.example.foodhelper.databinding.RvSearchRecipeBinding
import com.example.foodhelper.model.RecipePreview
import com.squareup.picasso.Picasso
import javax.inject.Inject


class SearchRecipeAdapter @Inject constructor() : ListAdapter<RecipePreview, SearchRecipeAdapter.SearchViewHolder>(SearchDiffCallback()) {

    private var onItemClickListener: ((recipeId: Int) -> Unit)? = null
    fun setOnItemClickListener(listener: ((recipeId: Int) -> Unit)) {
        onItemClickListener = listener
    }

    inner class SearchViewHolder(private val binding: RvSearchRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: RecipePreview) {
            binding.apply {
                tvRecipeTitle.text = currentItem.title
                tvRecipeCalories.text = binding.root.context.resources.getString(R.string.rv_search_recipe_calories, currentItem.calories)
                tvRecipeProtein.text = binding.root.context.resources.getString(R.string.rv_search_recipe_protein, currentItem.protein)
                Picasso.get().load(currentItem.previewImage).fit().centerCrop().into(ivRecipeImage)
                root.setOnClickListener {
                    onItemClickListener?.let { function -> function(currentItem.id) }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val rootView = RvSearchRecipeBinding.inflate(inflater, parent, false)
        return SearchViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }
}

class SearchDiffCallback : DiffUtil.ItemCallback<RecipePreview>() {
    override fun areItemsTheSame(oldItem: RecipePreview, newItem: RecipePreview): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RecipePreview, newItem: RecipePreview): Boolean {
        return oldItem == newItem
    }

}