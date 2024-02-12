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

    @Insert
    fun insertAll(foods:List<Food>)

    @Update
    fun update(food: Food)

    @Delete
    fun remove(food: Food)

    @Query("SELECT * FROM food_table")
    fun getAllFoods():List<Food>

    @Query("DELETE FROM food_table")
    fun deleteAll()

    @Query("SELECT * FROM food_table WHERE txtName LIKE '%' || :search || '%'")
    fun searchForFood(search:String):List<Food>
}