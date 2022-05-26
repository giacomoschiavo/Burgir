package com.example.burgir

import MenuScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.burgir.screen.SplashScreen
import com.example.burgir.ui.theme.BurgirTheme


class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      BurgirTheme() {
        Navigation()
      }
    }
  }

  companion object {
    val SPLASH_SCREEN_ROUTE = "splashScreen"
    val MENU_SCREEN_ROUTE = "menuScreen"
    val CART_SCREEN_ROUTE = "cartScreen"
    val PROFILE_SCREEN_ROUTE = "profileScreen"
    val PRODUCT_SCREEN_ROUTE = "productScreen"
  }
}

@Composable
fun Navigation() {
  val navController = rememberNavController()

  NavHost(navController = navController, startDestination = MainActivity.SPLASH_SCREEN_ROUTE) {
    composable(MainActivity.SPLASH_SCREEN_ROUTE) { SplashScreen(navController = navController) }
    composable(MainActivity.MENU_SCREEN_ROUTE) { MenuScreen(navController = navController) }
  }
}


@Preview
@Composable
fun HomeScreenPreview() {
  Navigation()
}