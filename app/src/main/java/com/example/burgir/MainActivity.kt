package com.example.burgir

import BottomBar
import FavoriteScreen
import HomeScreen
import MenuTopAppBar
import ProductTopBar
import SearchScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.burgir.screen.CartScreen
import com.example.burgir.screen.ProductScreen
import com.example.burgir.screen.ProfileScreen
import com.example.burgir.screen.SplashScreen
import com.example.burgir.ui.theme.BurgirTheme


class MainActivity : ComponentActivity() {
  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      BurgirTheme() {
        val navController = rememberNavController()
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        Scaffold(
          topBar = {
            when (currentRoute) {
              MENU_SCREEN_ROUTE, PROFILE_SCREEN_ROUTE, FAVORITE_SCREEN_ROUTE -> MenuTopAppBar(
                navController
              )
              SPLASH_SCREEN_ROUTE -> {}
              else -> ProductTopBar(navController = navController)
            }
          },
          bottomBar = { if (currentRoute == SPLASH_SCREEN_ROUTE) BottomBar(navController) },
        ) { innerPadding ->
          Column(
            modifier = Modifier.padding(
              vertical = innerPadding.calculateTopPadding(),
              horizontal = 15.dp
            )
          ) {
            NavHost(navController = navController, startDestination = SPLASH_SCREEN_ROUTE) {
              composable(SPLASH_SCREEN_ROUTE) { SplashScreen(navController = navController) }
              composable(MENU_SCREEN_ROUTE) { HomeScreen(navController = navController) }
              composable(PROFILE_SCREEN_ROUTE) { ProfileScreen(navController = navController) }
              composable(FAVORITE_SCREEN_ROUTE) { FavoriteScreen(navController = navController) }
              composable(CART_SCREEN_ROUTE) { CartScreen(navController = navController) }
              composable("${PRODUCT_SCREEN_ROUTE}/{productId}") { backStackEntry ->
                ProductScreen(
                  navController = navController,
                  backStackEntry.arguments?.getString("productId")!!.toInt()
                )
              }
              composable(SEARCH_SCREEN_ROUTE) { SearchScreen(navController = navController) }

            }
          }

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
    const val SEARCH_SCREEN_ROUTE = "searchScreen"
    const val FAVORITE_SCREEN_ROUTE = "favoriteScreen"
  }
}


@Preview
@Composable
fun HomeScreenPreview() {
  MainActivity()
}