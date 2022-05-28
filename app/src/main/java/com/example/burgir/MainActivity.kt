package com.example.burgir

import MenuScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.burgir.screen.CartScreen
import com.example.burgir.screen.ProductScreen
import com.example.burgir.screen.ProfileScreen
import com.example.burgir.screen.SplashScreen
import com.example.burgir.ui.theme.BurgirTheme


class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      BurgirTheme() {
        Surface() {
          Navigation()
        }
      }
    }
  }

  companion object {
    const val SPLASH_SCREEN_ROUTE = "splashScreen"
    const val MENU_SCREEN_ROUTE = "menuScreen"
    const val CART_SCREEN_ROUTE = "cartScreen"
    const val PROFILE_SCREEN_ROUTE = "profileScreen"
    const val PRODUCT_SCREEN_ROUTE = "productScreen"
  }
}

@Composable
fun Navigation() {
  val navController = rememberNavController()

  NavHost(navController = navController, startDestination = MainActivity.SPLASH_SCREEN_ROUTE) {
    composable(MainActivity.SPLASH_SCREEN_ROUTE) { SplashScreen(navController = navController) }
    composable(MainActivity.MENU_SCREEN_ROUTE) { MenuScreen(navController = navController) }
    composable(MainActivity.MENU_SCREEN_ROUTE) { MenuScreen(navController = navController) }
    composable(MainActivity.PROFILE_SCREEN_ROUTE) { ProfileScreen(navController = navController) }
    composable(MainActivity.CART_SCREEN_ROUTE) { CartScreen(navController = navController) }
    composable("${MainActivity.PRODUCT_SCREEN_ROUTE}/{productId}") { backStackEntry ->
      ProductScreen(
        navController = navController,
        backStackEntry.arguments?.getString("productId")!!.toInt()
      )
    }
  }
}


@Preview
@Composable
fun HomeScreenPreview() {
  Navigation()
}