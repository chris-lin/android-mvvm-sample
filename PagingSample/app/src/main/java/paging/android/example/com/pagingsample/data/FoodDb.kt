package paging.android.example.com.pagingsample.data

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.*
import android.content.Context
import paging.android.example.com.pagingsample.util.ioThread

/**
 * Singleton database object. Note that for a real app, you should probably use a Dependency
 * Injection framework or Service Locator to create the singleton database.
 */
@Database(entities = arrayOf(Food::class), version = 1)
abstract class FoodDb : RoomDatabase() {
    abstract fun foodDao(): FoodDao

    companion object {
        private var instance: FoodDb? = null
        @Synchronized
        fun get(context: Context): FoodDb {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                        FoodDb::class.java, "FoodDatabase")
                        .addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                fillInDb(context.applicationContext)
                            }
                        }).build()
            }
            return instance!!
        }

        /**
         * fill database with list of Food
         */
        private fun fillInDb(context: Context) {
            // inserts in Room are executed on the current thread, so we insert in the background
            ioThread {
                get(context).foodDao().insert(
                        FOOD_DATA.map { Food(id = 0, name = it) })
            }
        }
    }
}


private val FOOD_DATA = arrayListOf(
        "Pizza", "Dumpling", "Rice", "Noddle")
