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
import kotlin.math.roundToInt
import kotlin.random.Random

/**
 * This is the backend. The database.
 * The fact that this has very few comments emphasizes its coolness.
 */

/**
 * The database is developed using Room, which provides implementation once entities and data access objects are created
 */
@Database(entities = [Product::class, Category::class, Cart::class], version = 1, exportSchema = false)
abstract class BurgirRoomDatabase : RoomDatabase() {

  abstract fun productDao(): ProductDao
  abstract fun categoryDao(): CategoryDao
  abstract fun cartDao(): CartDao

  /**
   * class used to initialize the database with data
   * Categories and products are inserted in the database once the database is created.
   * When there is already an instance of the database saved, it is not re-created
   */
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
          /**
           * insert the five different categories with predefined attributes
           */
          categoryDao.insert(Category(1, "Burgers", R.drawable.b_bigmac, 25f, 0.96f, 1f))
          categoryDao.insert(Category(2, "Chickens", R.drawable.c_mcchicken, 120f, 0.7f, 1f))
          categoryDao.insert(Category(3, "Snacks", R.drawable.s_mcnuggets, 45f, 0.96f, 1f))
          categoryDao.insert(Category(4, "Ice creams", R.drawable.i_mcflurrybacio, 200f, 0.96f, 1f))
          categoryDao.insert(Category(5, "Drinks", R.drawable.d_cocacola, 285f, 0.96f, 1f))

          /**
           * resources like names and imagesURl used to insert products in the database
           */
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
                  /**
                   * discount set to a random value for demonstration purpose.
                   */
                  discount = Random.nextInt(0, 9) * 10,
                  /**
                   * product price random generated for demonstration purpose, instead of using a fixed price or a specific price for every product
                   */
                  productPrice = getRandomPrice(1,15),
                  isFavorite = false,
                  /**
                   * times purchased set to a random value instead of 0 for demonstration purpose, in order to show immediately some popular products
                   */
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

  /**
   * the database is created just one time, to follow the singleton pattern
   */
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

/**
 * helper function used to generate random prices within the interval [min,max]
 */
fun getRandomPrice(min:Int, max: Int) : Double {
  /**
   * random number generated
   */
  var num = min + Random.nextDouble() * (max - min)

  /**
   * rounding up the number to two decimal places
   */
  val final_price = (num * 10.0).roundToInt() / 10.0

  return final_price
}