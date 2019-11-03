package paging.android.example.com.pagingsample.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.Config
import androidx.paging.toLiveData
import paging.android.example.com.pagingsample.data.Food
import paging.android.example.com.pagingsample.data.FoodDb
import paging.android.example.com.pagingsample.util.ioThread

/**
 * A simple ViewModel that provides a paged list of delicious Cheeses.
 */
class FoodViewModel(app: Application) : AndroidViewModel(app) {
    private val _addbutton = MutableLiveData("Add")
    private val _chooseButton = MutableLiveData("Choose")

    val addbutton: LiveData<String> = _addbutton
    val chooseButton: LiveData<String> = _chooseButton

    val dao = FoodDb.get(app).foodDao()

    val eatItem = MutableLiveData(0)

    var foodSize = 0
    /**
     * We use -ktx Kotlin extension functions here, otherwise you would use LivePagedListBuilder(),
     * and PagedList.Config.Builder()
     */
    val allFoods = dao.allFoodByName().toLiveData(Config(
            /**
             * A good page size is a value that fills at least a screen worth of content on a large
             * device so the User is unlikely to see a null item.
             * You can play with this constant to observe the paging behavior.
             * <p>
             * It's possible to vary this with list device size, but often unnecessary, unless a
             * user scrolling on a large device is expected to scroll through items more quickly
             * than a small device, such as when the large device uses a grid layout of items.
             */
            pageSize = 30,

            /**
             * If placeholders are enabled, PagedList will report the full size but some items might
             * be null in onBind method (PagedListAdapter triggers a rebind when data is loaded).
             * <p>
             * If placeholders are disabled, onBind will never receive null but as more pages are
             * loaded, the scrollbars will jitter as new pages are loaded. You should probably
             * disable scrollbars if you disable placeholders.
             */
            enablePlaceholders = true,

            /**
             * Maximum number of items a PagedList should hold in memory at once.
             * <p>
             * This number triggers the PagedList to start dropping distant pages as more are loaded.
             */
            maxSize = 200))

    fun insert(text: CharSequence) = ioThread {
        dao.insert(Food(id = 0, name = text.toString()))
    }

    fun remove(cheese: Food) = ioThread {
        dao.delete(cheese)
    }

    fun selectorItem(number: Int) {
        // selector.value?.plus(1)
        // _selector.value = (_selector.value ?: 0) + 1
        eatItem.value = number
    }

    fun onChoose() {
        println("onChoose")
        val ran = (0 until foodSize).random()
        selectorItem(ran)
    }
}
