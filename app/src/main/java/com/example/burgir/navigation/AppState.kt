package com.example.burgir.navigation

import CategoryUiState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.burgir.MainActivity
import com.example.burgir.R
import com.example.burgir.data.Product

class AppState(
  var navController: NavController,
  var products: List<Product>
) {

  val categories = listOf(
    CategoryUiState(
      id = 0,
      name = "Burger",
      imageRes = R.drawable.b_bigmac,
      color = Color.hsv(25f, 0.96f, 1f)
    ),
    CategoryUiState(
      id = 1,
      name = "Chickens",
      imageRes = R.drawable.c_mcchicken,
      color = Color.hsv(120f, 0.7f, 1f)
    ),
    CategoryUiState(
      id = 2,
      name = "Snacks",
      imageRes = R.drawable.s_mcnuggets,
      color = Color.hsv(45f, 0.96f, 1f)
    ),
    CategoryUiState(
      id = 3,
      name = "Ice Creams",
      imageRes = R.drawable.i_mcflurrybacio,
      color = Color.hsv(200f, 0.96f, 1f)
    ),
    CategoryUiState(
      id = 4,
      name = "Drinks",
      imageRes = R.drawable.d_cocacola_m,
      color = Color.hsv(285f, 0.96f, 1f)
    ),

    )

  @Composable
  fun getCurrentRoute(): String {
    val backStackEntry by navController.currentBackStackEntryAsState()
    return backStackEntry?.destination?.route ?: ""
  }

  @Composable
  fun getArgumentsFor(arg: String): Int {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val int = backStackEntry?.arguments?.getString(arg)!!.toInt()
    return int
  }

  val navigateToProduct: (Int) -> Unit = {
    navController.navigate("${MainActivity.PRODUCT_SCREEN_ROUTE}/$it") { launchSingleTop }
  }

  val navigateToCategory: (Int) -> Unit = {
    navController.navigate("${MainActivity.CATEGORY_SCREEN_ROUTE}/$it") { launchSingleTop }
  }

  @Composable
  fun isFixedTopAppBar(): Boolean {
    return when (getCurrentRoute()) {
      MainActivity.MENU_SCREEN_ROUTE, MainActivity.SEARCH_SCREEN_ROUTE, MainActivity.FAVORITE_SCREEN_ROUTE, MainActivity.PROFILE_SCREEN_ROUTE -> true
      else -> false
    }
  }


  fun getCategoryNameById(categoryId: Int): String {
    if (categoryId == -1) return ""
    val name = categories.find { category ->
      category.id == categoryId
    }?.name
    return name ?: ""
  }


}
