package com.example.burgir

import MainNavigationBar
import CategoryScreen
import FavoriteScreen
import HomeScreen
import MenuTopAppBar
import Product
import ProductTopBar
import SearchScreen
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.burgir.data.BurgirRoomDatabase
import com.example.burgir.data.*
import com.example.burgir.screen.*
import com.example.burgir.ui.theme.BurgirTheme
import com.google.android.material.color.DynamicColors
import kotlinx.coroutines.flow.Flow


class MainActivity : ComponentActivity() {

  private val myViewModel: BurgirViewModel by viewModels {
    BurgirViewModelFactory((application as BurgirApplication).repository)
  }
  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    /**
     * creazione database, PROVA CRASH
     */


    DynamicColors.applyIfAvailable(this)
    val burgerImageTypedArray: TypedArray = resources.obtainTypedArray(R.array.burgers)
    val burgerNamesTypedArray: TypedArray = resources.obtainTypedArray(R.array.burgers_names)
    val burgers = mutableListOf<Product>()
    (0 until burgerNamesTypedArray.length()).forEach {
      burgers.add(
        Product(
          id = it,
          imageUrl = burgerImageTypedArray.getResourceId(it, 0),
          name = burgerNamesTypedArray.getString(it)!!,
          categoryId = 0
        )
      )
    }
    burgerImageTypedArray.recycle()
    burgerNamesTypedArray.recycle()

    setContent {
      BurgirTheme() {
        val navController = rememberNavController()
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        val navigateToProduct: (Int) -> Unit =
          { navController.navigate("${PRODUCT_SCREEN_ROUTE}/$it") { launchSingleTop } }
        val navigateToCategory: (Int) -> Unit =
          { navController.navigate("${CATEGORY_SCREEN_ROUTE}/$it") { launchSingleTop } }

        val decayAnimationSpec = rememberSplineBasedDecay<Float>()
        val scrollBehavior = remember(decayAnimationSpec) {
          TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
        }
        Scaffold(
          modifier = if (currentRoute?.substringBefore("/") == CATEGORY_SCREEN_ROUTE) Modifier.nestedScroll(
            scrollBehavior.nestedScrollConnection
          ) else Modifier,
          topBar = {
            when (currentRoute?.substringBefore("/")) {
              PRODUCT_SCREEN_ROUTE -> ProductTopBar(
                navController = navController,
                scrollBehavior = scrollBehavior
              )
              CATEGORY_SCREEN_ROUTE -> ProductTopBar(
                navController = navController,
                showFavoriteIcon = false,
                scrollBehavior = scrollBehavior,
                selectedCategoryId = backStackEntry?.arguments?.getString("categoryId")!!.toInt(),
              )
              SPLASH_SCREEN_ROUTE -> {}
              else -> MenuTopAppBar(navController)
            }
          },
          bottomBar = {
            when (currentRoute) {
              PRODUCT_SCREEN_ROUTE, SPLASH_SCREEN_ROUTE, CATEGORY_SCREEN_ROUTE, CART_SCREEN_ROUTE -> {}
              else -> MainNavigationBar(navController)
            }
          },
        ) { innerPadding ->
          NavHost(
            navController = navController,
            startDestination = SPLASH_SCREEN_ROUTE,
            modifier = Modifier
              .padding(innerPadding)
              .padding(horizontal = 15.dp),
          ) {
            composable(SPLASH_SCREEN_ROUTE) { SplashScreen(navController = navController) }
            composable(MENU_SCREEN_ROUTE) { HomeScreen(navigateToProduct, burgers) }
            composable(PROFILE_SCREEN_ROUTE) { ProfileScreen(navController = navController) }
            composable(FAVORITE_SCREEN_ROUTE) { FavoriteScreen(navController = navController) }
            composable(CART_SCREEN_ROUTE) { CartScreen(navController = navController) }
            composable(SEARCH_SCREEN_ROUTE) { SearchScreen(navigateToCategory = navigateToCategory) }
            composable("${PRODUCT_SCREEN_ROUTE}/{productId}") { backStackEntry ->
              ProductDetailsScreen(
                productId = backStackEntry.arguments?.getString("productId")!!.toInt()
              )
            }
            composable("${CATEGORY_SCREEN_ROUTE}/{categoryId}") { backStackEntry ->
              CategoryScreen(
                categoryId = backStackEntry.arguments?.getString("categoryId")!!.toInt(),
                navigateToProduct = navigateToProduct
              )
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
    const val CATEGORY_SCREEN_ROUTE = "categoryScreen"
  }
}