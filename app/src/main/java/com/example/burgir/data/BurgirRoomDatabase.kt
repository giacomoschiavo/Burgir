package com.example.burgir.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Product::class,Category::class,Cart::class], version=1, exportSchema = false )
abstract class BurgirRoomDatabase : RoomDatabase() {
    /**
     * Dao getters
     */
    abstract fun productDao() : ProductDao
    abstract fun categoryDao() : CategoryDao
    abstract fun cartDao() : CartDao

    companion object {
        @Volatile
        private var INSTANCE: BurgirRoomDatabase? = null
        fun getDatabase(context: Context): BurgirRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BurgirRoomDatabase::class.java,
                    "burgir_database"
                )   .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}