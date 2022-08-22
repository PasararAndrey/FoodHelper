package com.example.foodhelper.ui.recipedetails.ingredients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodhelper.R
import com.example.foodhelper.databinding.RvIngredientsBinding
import com.example.foodhelper.model.RecipeIngredient
import com.squareup.picasso.Picasso
import javax.inject.Inject

class IngredientsAdapter @Inject constructor() : ListAdapter<RecipeIngredient, IngredientsAdapter.IngredientsViewHolder>(IngredientDiffCallback()) {

    class IngredientsViewHolder(private val binding: RvIngredientsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ingredient: RecipeIngredient) {
            Picasso.get().load(ingredient.image)
                .placeholder(AppCompatResources.getDrawable(this@IngredientsViewHolder.itemView.context,
                    R.drawable.ic_baseline_image_not_supported_24)!!)
                .into(binding.ingredientImage)
            binding.ingredientText.text = this@IngredientsViewHolder.itemView.context.getString(R.string.rv_ingredients_description,
                ingredient.ingredientMeasure.amount.toString(), ingredient.ingredientMeasure.unitShort, ingredient.name
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvIngredientsBinding.inflate(inflater, parent, false)
        return IngredientsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val currentIngredient = getItem(position)
        holder.bind(currentIngredient)
    }
}

private class IngredientDiffCallback : DiffUtil.ItemCallback<RecipeIngredient>() {
    override fun areItemsTheSame(oldItem: RecipeIngredient, newItem: RecipeIngredient): Boolean {
        return oldItem.ingredientId == newItem.ingredientId
    }

    override fun areContentsTheSame(oldItem: RecipeIngredient, newItem: RecipeIngredient): Boolean {
        return oldItem == newItem
    }

}