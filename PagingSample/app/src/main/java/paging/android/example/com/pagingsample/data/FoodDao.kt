package paging.android.example.com.pagingsample.data

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * Database Access Object for the Food database.
 */
@Dao
interface FoodDao {
    /**
     * Room knows how to return a LivePagedListProvider, from which we can get a LiveData and serve
     * it back to UI via ViewModel.
     */
    @Query("SELECT * FROM Food ORDER BY name COLLATE NOCASE ASC")
    fun allFoodByName(): DataSource.Factory<Int, Food>

    @Insert
    fun insert(food: List<Food>)

    @Insert
    fun insert(food: Food)

    @Delete
    fun delete(food: Food)
}