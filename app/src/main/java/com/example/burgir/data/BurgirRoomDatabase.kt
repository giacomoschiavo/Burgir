package com.example.burgir.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * This is the backend. The database. This used to be done by the OpenHelper.
 * The fact that this has very few comments emphasizes its coolness.
 */
@Database(entities = [Product::class,Category::class,Cart::class], version = 1)
abstract class BurgirRoomDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun categoryDao() : CategoryDao
    abstract fun cartDao(): CartDao

    private class BurgirDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var productDao = database.productDao()
                    var categoryDao = database.categoryDao()

                    categoryDao.insert(Category(0,"Burgers",25f,0.96f,1f))
                    categoryDao.insert(Category(0,"Chickens",120f,0.7f,1f))
                    categoryDao.insert(Category(0,"Snacks",45f,0.96f,1f))
                    categoryDao.insert(Category(0,"Ice creams",200f,0.96f,1f))
                    categoryDao.insert(Category(0,"Drinks",285f,0.96f,1f))
                }
            }
        }
    }
    companion object {
        @Volatile
        private var INSTANCE: BurgirRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): BurgirRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BurgirRoomDatabase::class.java,
                    "burgir_database"
                )
                    .addCallback(BurgirDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}