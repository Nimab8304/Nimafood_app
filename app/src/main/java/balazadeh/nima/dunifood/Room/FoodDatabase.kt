package balazadeh.nima.dunifood.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import balazadeh.nima.dunifood.Food

@Database(version = 1, exportSchema = false, entities = [Food::class])
abstract class FoodDatabase : RoomDatabase() {
    abstract val foodDao: FoodDao


    companion object {
        private val database: FoodDatabase? = null
        fun getDatabase(context: Context): FoodDatabase {
            var instance = database
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodDatabase::class.java,
                    "myDatabase.db"
                ).build()
            }
            return instance
        }
    }
}