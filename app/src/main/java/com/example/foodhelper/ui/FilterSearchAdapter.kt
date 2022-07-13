package com.example.foodhelper.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.foodhelper.R
import com.example.foodhelper.databinding.RvFilterItemBinding
import com.example.foodhelper.model.FilterSearchItem
import java.util.*

class FilterSearchAdapter(private val onClick: (position: Int, isChecked: Boolean) -> Unit) :
    RecyclerView.Adapter<FilterSearchAdapter.FilterViewHolder>() {
    private val mFilterItems: MutableList<FilterSearchItem> = LinkedList()

    fun setData(filterItems: List<FilterSearchItem>) {
        mFilterItems.clear()
        mFilterItems.addAll(filterItems)
        notifyDataSetChanged()
    }

    class FilterViewHolder(private val binding: RvFilterItemBinding, onClick: (position: Int, isChecked: Boolean) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        private var currentItem: FilterSearchItem? = null

        init {
            binding.root.setOnClickListener {
                currentItem?.let {
                    onClick(adapterPosition, it.isChecked)
                }
            }
        }

        fun bind(filterSearchItem: FilterSearchItem) {
            currentItem = filterSearchItem
            binding.tvFilter.text = filterSearchItem.itemText
            if (filterSearchItem.isChecked) {
                binding.tvFilter.apply {
                    background = AppCompatResources.getDrawable(this@FilterViewHolder.itemView.context, R.drawable.bg_tv_filter_item_filled)
                    setTextColor(ResourcesCompat.getColor(resources, R.color.white, null))
                }

            } else {
                binding.tvFilter.apply {
                    background = AppCompatResources.getDrawable(this@FilterViewHolder.itemView.context, R.drawable.bg_tv_filter_item_outlined)
                    setTextColor(ResourcesCompat.getColor(resources, R.color.bice_green, null))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = RvFilterItemBinding.inflate(inflater, parent, false)
        return FilterViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val currentItem = mFilterItems[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return mFilterItems.count()
    }

}