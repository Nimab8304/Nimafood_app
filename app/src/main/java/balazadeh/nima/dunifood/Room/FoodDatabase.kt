package balazadeh.nima.dunifood.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import balazadeh.nima.dunifood.Food

@Database(version = 1 , exportSchema = false, entities = [Food::class] )
abstract class FoodDatabase:RoomDatabase() {
}