package paging.android.example.com.pagingsample.ui

import android.view.LayoutInflater
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import paging.android.example.com.pagingsample.R
import paging.android.example.com.pagingsample.data.Food
import paging.android.example.com.pagingsample.databinding.CheeseItemBinding
import paging.android.example.com.pagingsample.viewmodel.FoodViewHolder
import paging.android.example.com.pagingsample.viewmodel.FoodViewModel

/**
 * A simple PagedListAdapter that binds Cheese items into CardViews.
 * <p>
 * PagedListAdapter is a RecyclerView.Adapter base class which can present the content of PagedLists
 * in a RecyclerView. It requests new pages as the user scrolls, and handles new PagedLists by
 * computing list differences on a background thread, and dispatching minimal, efficient updates to
 * the RecyclerView to ensure minimal UI thread work.
 * <p>
 * If you want to use your own Adapter base class, try using a PagedListAdapterHelper inside your
 * adapter instead.
 *
 * @see android.arch.paging.PagedListAdapter
 * @see android.arch.paging.AsyncPagedListDiffer
 */
//class FoodAdapter : PagedListAdapter<Food, FoodViewHolder>(diffCallback) {
class FoodAdapter(private val viewModel: FoodViewModel, private val parentLifecycleOwner: LifecycleOwner) :
        PagedListAdapter<Food, FoodViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        println("onBindViewHolder")

        holder.binding.viewmodel = viewModel
        holder.binding.position = position
        holder.binding.lifecycleOwner = parentLifecycleOwner

        holder.bindTo(getItem(position))
    }

    // override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder = FoodViewHolder(parent)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        println("onCreateViewHolder")

        val binding = createBinding(parent, viewType)

        return FoodViewHolder(binding)
    }

    private fun createBinding(parent: ViewGroup, viewType: Int): CheeseItemBinding {
        return DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.cheese_item,
                parent,
                false
        )
    }

    companion object {
        /**
         * This diff callback informs the PagedListAdapter how to compute list differences when new
         * PagedLists arrive.
         * <p>
         * When you add a Cheese with the 'Add' button, the PagedListAdapter uses diffCallback to
         * detect there's only a single item difference from before, so it only needs to animate and
         * rebind a single view.
         *
         * @see android.support.v7.util.DiffUtil
         */
        private val diffCallback = object : DiffUtil.ItemCallback<Food>() {
            override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean =
                    oldItem.id == newItem.id

            /**
             * Note that in kotlin, == checking on data classes compares all contents, but in Java,
             * typically you'll implement Object#equals, and use it to compare object contents.
             */
            override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean =
                    oldItem == newItem
        }
    }
}
