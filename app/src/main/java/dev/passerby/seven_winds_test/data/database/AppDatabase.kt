package dev.passerby.seven_winds_test.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.passerby.seven_winds_test.data.models.db.CoffeeHouseItemDbModel

@Database(
    entities = [CoffeeHouseItemDbModel::class],
    exportSchema = false,
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun coffeeHousesDao(): CoffeeHousesDao

    companion object {
        private var db: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "main.db"

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(
                    context, AppDatabase::class.java, DB_NAME
                ).fallbackToDestructiveMigration().build()
                db = instance
                return instance
            }
        }
    }
}