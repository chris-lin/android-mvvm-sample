package paging.android.example.com.pagingsample.viewmodel

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import paging.android.example.com.pagingsample.R
import paging.android.example.com.pagingsample.data.Food
import paging.android.example.com.pagingsample.databinding.CheeseItemBinding

/**
 * A simple ViewHolder that can bind a Cheese item. It also accepts null items since the data may
 * not have been fetched before it is bound.
 */
//class FoodViewHolder(parent :ViewGroup) : RecyclerView.ViewHolder(
//        LayoutInflater.from(parent.context).inflate(R.layout.cheese_item, parent, false)) {
class FoodViewHolder(val binding: CheeseItemBinding) : RecyclerView.ViewHolder(
        binding.root) {

    private val nameView = itemView.findViewById<TextView>(R.id.name)
    var food: Food? = null

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(food: Food?) {
        this.food = food
        nameView.text = food?.name
    }
}