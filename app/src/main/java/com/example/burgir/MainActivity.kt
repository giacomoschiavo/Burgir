package com.example.burgir

import NavigationController
import android.content.res.TypedArray
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.burgir.data.Product
import com.example.burgir.ui.theme.BurgirTheme
import com.google.android.material.color.DynamicColors


class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

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