package com.example.burgir

import NavigationController
import android.content.res.Resources
import android.content.res.TypedArray
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import com.example.burgir.data.*
import com.example.compose.BurgirTheme
import com.google.android.material.color.DynamicColors
import kotlin.random.Random


class MainActivity : ComponentActivity() {

  private val myViewModel: BurgirViewModel by viewModels {
    BurgirViewModelFactory((application as BurgirApplication).repository)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    var xxx = listOf<Product>()
    myViewModel.products.observe(this, { products -> xxx = products })

    var products = loadAllProducts(resources)

    DynamicColors.applyIfAvailable(this)
    setContent {
      BurgirTheme() {
        Surface() {
          NavigationController(products)
        }
      }
    }
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
      products.add(
        Product(
          id = indexes++,
          imageUrl = typedArray.getResourceId(it, 0),
          productName = arrays[i + 1].getString(it)!!,
          category = i / 2,
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

  return products
}