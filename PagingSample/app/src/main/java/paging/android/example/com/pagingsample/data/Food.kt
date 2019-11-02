package paging.android.example.com.pagingsample.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class that represents our items.
 */
@Entity
data class Food(@PrimaryKey(autoGenerate = true) val id: Int, val name: String)