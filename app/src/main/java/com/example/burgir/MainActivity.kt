package com.example.burgir

import NavigationController
import android.content.res.Resources
import android.content.res.TypedArray
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.Observer
import com.example.burgir.data.BurgirApplication
import com.example.burgir.data.BurgirViewModel
import com.example.burgir.data.BurgirViewModelFactory
import com.example.burgir.data.Product
import com.example.burgir.ui.theme.BurgirTheme
import com.google.android.material.color.DynamicColors
import kotlinx.coroutines.flow.Flow
import kotlin.random.Random


class MainActivity : ComponentActivity() {

  //  private val myViewModel: BurgirViewModel by viewModels {
//    BurgirViewModelFactory((application as BurgirApplication).repository)
//  }
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

//    var xxx = listOf<Product>()
//    myViewModel.products.observe(this, { products -> xxx = products })

    var products = loadAllProducts(resources)

    DynamicColors.applyIfAvailable(this)
    setContent {
      BurgirTheme {
        NavigationController(products)
      }
    }
  }


  companion object {
    const val SPLASH_SCREEN_ROUTE = "splashScreen"
    const val MENU_SCREEN_ROUTE = "menuScreen"
    const val CART_SCREEN_ROUTE = "cartScreen"
    const val PROFILE_SCREEN_ROUTE = "profileScreen"
    const val PRODUCT_SCREEN_ROUTE = "productScreen"
    const val SEARCH_SCREEN_ROUTE = "searchScreen"
    const val FAVORITE_SCREEN_ROUTE = "favoriteScreen"
    const val CATEGORY_SCREEN_ROUTE = "categoryScreen"
  }
}

fun loadAllProducts(resources: Resources): List<Product> {
  val burgerImageTypedArray: TypedArray = resources.obtainTypedArray(R.array.burgers)
  val burgerNamesTypedArray: TypedArray = resources.obtainTypedArray(R.array.burgers_names)
  val snacksImageTypedArray: TypedArray = resources.obtainTypedArray(R.array.snacks)
  val snacksNamesTypedArray: TypedArray = resources.obtainTypedArray(R.array.snacks_names)
  val chickensImageTypedArray: TypedArray = resources.obtainTypedArray(R.array.chickens)
  val chickensNamesTypedArray: TypedArray = resources.obtainTypedArray(R.array.chickens_names)
  val iceCreamsImageTypedArray: TypedArray = resources.obtainTypedArray(R.array.ice_creams)
  val iceCreamsNamesTypedArray: TypedArray = resources.obtainTypedArray(R.array.ice_creams_names)
  val drinksImageTypedArray: TypedArray = resources.obtainTypedArray(R.array.drinks)
  val drinksNamesTypedArray: TypedArray = resources.obtainTypedArray(R.array.drinks_names)
  val products = mutableListOf<Product>()

  val arrays = listOf(
    burgerImageTypedArray,
    burgerNamesTypedArray,
    chickensImageTypedArray,
    chickensNamesTypedArray,
    drinksImageTypedArray,
    drinksNamesTypedArray,
    iceCreamsImageTypedArray,
    iceCreamsNamesTypedArray,
    snacksImageTypedArray,
    snacksNamesTypedArray
  )

  var indexes = 0
  for (i in arrays.indices step 2) {
    Log.e("TAG", i.toString())
    val typedArray = arrays[i]
    (0 until typedArray.length()).forEach {
      products.add(
        Product(
          id = indexes++,
          imageUrl = typedArray.getResourceId(it, 0),
          productName = arrays[i + 1].getString(it)!!,
          category = i / 2,
          discount = 0,
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

  return products
}