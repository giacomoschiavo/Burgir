package com.example.burgir.data

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.burgir.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random

/**
 * This is the backend. The database. This used to be done by the OpenHelper.
 * The fact that this has very few comments emphasizes its coolness.
 */
@Database(entities = [Product::class, Category::class, Cart::class], version = 1)
abstract class BurgirRoomDatabase : RoomDatabase() {

  abstract fun productDao(): ProductDao
  abstract fun categoryDao(): CategoryDao
  abstract fun cartDao(): CartDao

  private class BurgirDatabaseCallback(
    private val scope: CoroutineScope,
    private val resources: Resources
  ) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
      super.onCreate(db)
      INSTANCE?.let { database ->
        scope.launch {
          var productDao = database.productDao()
          var categoryDao = database.categoryDao()

          categoryDao.insert(Category(1, "Burgers", R.drawable.b_bigmac, 25f, 0.96f, 1f))
          categoryDao.insert(Category(2, "Chickens", R.drawable.c_mcchicken, 120f, 0.7f, 1f))
          categoryDao.insert(Category(3, "Snacks", R.drawable.s_mcnuggets, 45f, 0.96f, 1f))
          categoryDao.insert(Category(4, "Ice creams", R.drawable.i_mcflurrybacio, 200f, 0.96f, 1f))
          categoryDao.insert(Category(5, "Drinks", R.drawable.d_cocacola, 285f, 0.96f, 1f))

          val burgerImageTypedArray: TypedArray =
            resources.obtainTypedArray(R.array.burgers)
          val burgerNamesTypedArray: TypedArray =
            resources.obtainTypedArray(R.array.burgers_names)
          val snacksImageTypedArray: TypedArray =
            resources.obtainTypedArray(R.array.snacks)
          val snacksNamesTypedArray: TypedArray =
            resources.obtainTypedArray(R.array.snacks_names)
          val chickensImageTypedArray: TypedArray =
            resources.obtainTypedArray(R.array.chickens)
          val chickensNamesTypedArray: TypedArray =
            resources.obtainTypedArray(R.array.chickens_names)
          val iceCreamsImageTypedArray: TypedArray =
            resources.obtainTypedArray(R.array.ice_creams)
          val iceCreamsNamesTypedArray: TypedArray =
            resources.obtainTypedArray(R.array.ice_creams_names)
          val drinksImageTypedArray: TypedArray =
            resources.obtainTypedArray(R.array.drinks)
          val drinksNamesTypedArray: TypedArray =
            resources.obtainTypedArray(R.array.drinks_names)

          val arrays = listOf(
            burgerImageTypedArray,
            burgerNamesTypedArray,
            chickensImageTypedArray,
            chickensNamesTypedArray,
            snacksImageTypedArray,
            snacksNamesTypedArray,
            iceCreamsImageTypedArray,
            iceCreamsNamesTypedArray,
            drinksImageTypedArray,
            drinksNamesTypedArray,
          )

          var indexes = 0
          for (i in arrays.indices step 2) {
            val typedArray = arrays[i]
            (0 until typedArray.length()).forEach {
              productDao.insert(
                Product(
                  id = indexes++,
                  imageUrl = typedArray.getResourceId(it, 0),
                  productName = arrays[i + 1].getString(it)!!,
                  category = i / 2 + 1,
                  discount = Random.nextInt(0, 9) * 10,
                  isFavorite = Random.nextBoolean(),
                  timesPurchased = Random.nextInt(0, 5)
                )
              )
            }
          }


          burgerImageTypedArray.recycle()
          burgerNamesTypedArray.recycle()
          chickensImageTypedArray.recycle()
          chickensNamesTypedArray.recycle()
          drinksImageTypedArray.recycle()
          drinksNamesTypedArray.recycle()
          iceCreamsImageTypedArray.recycle()
          iceCreamsNamesTypedArray.recycle()
          snacksImageTypedArray.recycle()
          snacksNamesTypedArray.recycle()
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
          .addCallback(BurgirDatabaseCallback(scope, context.resources))
          .build()
        INSTANCE = instance
        // return instance
        instance
      }
    }
  }
}