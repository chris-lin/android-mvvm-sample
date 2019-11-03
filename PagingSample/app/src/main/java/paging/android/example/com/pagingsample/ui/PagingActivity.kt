package paging.android.example.com.pagingsample.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import paging.android.example.com.pagingsample.R
import paging.android.example.com.pagingsample.viewmodel.FoodViewHolder
import paging.android.example.com.pagingsample.viewmodel.FoodViewModel
import androidx.lifecycle.ViewModelProviders
import paging.android.example.com.pagingsample.databinding.ActivityMainBinding
import kotlin.random.Random

/**
 * Shows a list of Cheeses, with swipe-to-delete, and an input field at the top to add.
 * <p>
 * Cheeses are stored in a database, so swipes and additions edit the database directly, and the UI
 * is updated automatically using paging components.
 */
class PagingActivity : AppCompatActivity() {

//    AndroidX libraries use this lightweight import for Lifecycle
//    private val viewModel by viewModels<FoodViewModel>()
//    private lateinit var viewModel: FoodViewModel
    private val viewModel: FoodViewModel by lazy {
        ViewModelProviders.of(this).get(FoodViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // An alternative ViewModel using Observable fields and @Bindable properties can be used:
        // val viewModel = ViewModelProviders.of(this).get(FoodViewModel::class.java)

        // Obtain binding
        val binding: ActivityMainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Bind layout with ViewModel
        binding.viewmodel = viewModel

        // LiveData needs the lifecycle owner
        binding.lifecycleOwner = this

        // Create adapter for the RecyclerView
        val adapter = FoodAdapter(viewModel, this)
        cheeseList.adapter = adapter

        // Subscribe the adapter to the ViewModel, so the items in the adapter are refreshed
        // when the list changes

        // viewModel.allFoods.observe(this, Observer(adapter::submitList))
        viewModel.allFoods.observe(this, Observer{pagedList->
            val size = pagedList.size
            viewModel.foodSize = size
            println("Total list size: $size")
            val ran = (0 until size).random()
            // val ran = (0 until 10).random() // 0 ~ 9
            // val ran = (0..10).random() // 0 ~ 10
            // val ran = Random.nextInt(0, 10) // 0 ~ 9
            println("selector item: $ran")
            viewModel.selectorItem(ran)
            adapter.submitList(pagedList)
        })

        initAddButtonListener()
        initSwipeToDelete()
    }

    private fun initSwipeToDelete() {

        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            // enable the items to swipe to the left or right
            override fun getMovementFlags(recyclerView: RecyclerView,
                                          viewHolder: RecyclerView.ViewHolder): Int =
                    makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean = false

            // When an item is swiped, remove the item via the view model. The list item will be
            // automatically removed in response, because the adapter is observing the live list.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                println("onSwiped")
                (viewHolder as FoodViewHolder).food?.let {
                    viewModel.remove(it)
                }
            }
        }).attachToRecyclerView(cheeseList)
    }

    private fun addCheese() {
        val newCheese = inputText.text.trim()
        if (newCheese.isNotEmpty()) {
            viewModel.insert(newCheese)
            inputText.setText("")
        }
    }

    private fun initAddButtonListener() {
        addButton.setOnClickListener {
            addCheese()
        }

        // when the user taps the "Done" button in the on screen keyboard, save the item.
        inputText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addCheese()
                return@setOnEditorActionListener true
            }
            false // action that isn't DONE occurred - ignore
        }
        // When the user clicks on the button, or presses enter, save the item.
        inputText.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                addCheese()
                return@setOnKeyListener true
            }
            false // event that isn't DOWN or ENTER occurred - ignore
        }
    }
}
