package balazadeh.nima.dunifood

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_table")
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id: Int?=null,
    val txtName: String,
    val txtPrice: String,
    val txtDistance: String,
    val txtCity: String,
    @ColumnInfo(name = "url")
    val urlImage: String,
    val numOfRating: Int,
    val raing: Float
)