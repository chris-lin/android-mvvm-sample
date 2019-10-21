/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package paging.android.example.com.pagingsample.data

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.*
import android.content.Context
import paging.android.example.com.pagingsample.util.ioThread

/**
 * Singleton database object. Note that for a real app, you should probably use a Dependency
 * Injection framework or Service Locator to create the singleton database.
 */
@Database(entities = arrayOf(Colleague::class), version = 1)
abstract class ColleagueDb : RoomDatabase() {
    abstract fun colleagueDao(): ColleagueDao

    companion object {
        private var instance: ColleagueDb? = null
        @Synchronized
        fun get(context: Context): ColleagueDb {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                        ColleagueDb::class.java, "ColleagueDatabase")
                        .addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                fillInDb(context.applicationContext)
                            }
                        }).build()
            }
            return instance!!
        }

        /**
         * fill database with list of colleague
         */
        private fun fillInDb(context: Context) {
            // inserts in Room are executed on the current thread, so we insert in the background
            ioThread {
                get(context).colleagueDao().insert(
                        COLLEAGUE_DATA.map { Colleague(id = 0, name = it) })
            }
        }
    }
}


private val COLLEAGUE_DATA = arrayListOf(
        "Alex", "David", "Chris", "Sean", "Will", "Jacky", "Giga", "Shawn", "Jungwei", "Jessie", "Susu", "Bella")
