package com.example.burgir

import NavigationController
import android.content.res.TypedArray
import android.os.Bundle
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


class MainActivity : ComponentActivity() {

  //  private val myViewModel: BurgirViewModel by viewModels {
//    BurgirViewModelFactory((application as BurgirApplication).repository)
//  }
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

//    var xxx = listOf<Product>()
//    myViewModel.products.observe(this, { products -> xxx = products })

    DynamicColors.applyIfAvailable(this)
    setContent {
      BurgirTheme {
        NavigationController(resources)
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