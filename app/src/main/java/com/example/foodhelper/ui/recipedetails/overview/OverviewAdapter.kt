package com.example.foodhelper.ui.recipedetails.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodhelper.databinding.RvOverviewItemBinding
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class OverviewAdapter @Inject constructor() : RecyclerView.Adapter<OverviewAdapter.OverViewVH>() {
    private val mItemsList: MutableList<String> = LinkedList()

    fun setData(newItems: List<String>) {
        mItemsList.clear()
        mItemsList.addAll(newItems)
        notifyDataSetChanged()
    }

    class OverViewVH(private val binding: RvOverviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: String) {
            binding.itemText.text = currentItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverViewVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvOverviewItemBinding.inflate(inflater, parent, false)
        return OverViewVH(binding)
    }

    override fun onBindViewHolder(holder: OverViewVH, position: Int) {
        val currentItem = mItemsList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return mItemsList.count()
    }

    companion object {
        private const val TAG = "OverviewAdapter"
    }
}
