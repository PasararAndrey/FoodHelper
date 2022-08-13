package com.example.foodhelper.ui.recipedetails.steps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodhelper.R
import com.example.foodhelper.databinding.RvRecipeDetailsStepsBinding
import com.example.foodhelper.model.RecipeStep
import java.util.*
import javax.inject.Inject

class StepsAdapter @Inject constructor() : RecyclerView.Adapter<StepsAdapter.StepsVH>() {
    private val mSteps: MutableList<RecipeStep> = LinkedList()

    fun setData(newSteps: List<RecipeStep>) {
        mSteps.clear()
        mSteps.addAll(newSteps)
        notifyDataSetChanged()
    }

    class StepsVH(private val binding: RvRecipeDetailsStepsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: RecipeStep) {
            binding.tvStep.text =
                this@StepsVH.itemView.context.resources.getString(R.string.tv_recipe_details_step, currentItem.number, currentItem.step)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvRecipeDetailsStepsBinding.inflate(inflater, parent, false)
        return StepsVH(binding)
    }

    override fun onBindViewHolder(holder: StepsVH, position: Int) {
        val currentItem = mSteps[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return mSteps.count()
    }
}