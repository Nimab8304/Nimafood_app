package balazadeh.nima.dunifood.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import balazadeh.nima.dunifood.Food

@Dao
interface FoodDao {
    @Insert
    fun insert(food: Food)

    @Update
    fun update(food: Food)

    @Delete
    fun remove(food: Food)

//    @Query( )
//    fun getAllFoods():List<Food>
}