package com.example.burgir.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.burgir.data.Category
import com.example.burgir.data.Product

class AppState(
  var navController: NavController,
  var products: List<Product>,
  var categories: List<Category>
) {
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
    navController.navigate("${PRODUCT_SCREEN_ROUTE}/$it") { launchSingleTop }
  }

  val navigateToCategory: (Int) -> Unit = {
    navController.navigate("${CATEGORY_SCREEN_ROUTE}/$it") { launchSingleTop }
  }

  @Composable
  fun isFixedTopAppBar(): Boolean {
    return when (getCurrentRoute()) {
      MENU_SCREEN_ROUTE, SEARCH_SCREEN_ROUTE, FAVORITE_SCREEN_ROUTE, PROFILE_SCREEN_ROUTE -> true
      else -> false
    }
  }


  fun getCategoryNameById(categoryId: Int): String {
    if (categoryId == -1) return ""
    val name = categories.find { category ->
      category.id == categoryId
    }?.categoryName
    return name ?: ""
  }


  companion object RouteConfig {
    const val SPLASH_SCREEN_ROUTE = "splashScreen"
    const val MENU_SCREEN_ROUTE = "menuScreen"
    const val CART_SCREEN_ROUTE = "cartScreen"
    const val PROFILE_SCREEN_ROUTE = "profileScreen"
    const val PRODUCT_SCREEN_ROUTE = "productScreen"
    const val SEARCH_SCREEN_ROUTE = "searchScreen"
    const val FAVORITE_SCREEN_ROUTE = "favoriteScreen"
    const val CATEGORY_SCREEN_ROUTE = "categoryScreen"

//    val categories = listOf(
//      CategoryUiState(
//        id = 0,
//        name = "Burger",
//        imageRes = R.drawable.b_bigmac,
//        color = Color.hsv(25f, 0.96f, 1f)
//      ),
//      CategoryUiState(
//        id = 1,
//        name = "Chickens",
//        imageRes = R.drawable.c_mcchicken,
//        color = Color.hsv(120f, 0.7f, 1f)
//      ),
//      CategoryUiState(
//        id = 2,
//        name = "Snacks",
//        imageRes = R.drawable.s_mcnuggets,
//        color = Color.hsv(45f, 0.96f, 1f)
//      ),
//      CategoryUiState(
//        id = 3,
//        name = "Ice Creams",
//        imageRes = R.drawable.i_mcflurrybacio,
//        color = Color.hsv(200f, 0.96f, 1f)
//      ),
//      CategoryUiState(
//        id = 4,
//        name = "Drinks",
//        imageRes = R.drawable.d_cocacola_m,
//        color = Color.hsv(285f, 0.96f, 1f)
//      ),
//
//      )


    val routes = listOf(
      RouteConfig(
        "Home",
        MENU_SCREEN_ROUTE,
        filledIcon = Icons.Filled.Home,
        outlinedIcon = Icons.Outlined.Home
      ),
      RouteConfig(
        "Search",
        SEARCH_SCREEN_ROUTE,
        filledIcon = Icons.Filled.Search,
        outlinedIcon = Icons.Outlined.Search
      ),
      RouteConfig(
        "Favorite",
        FAVORITE_SCREEN_ROUTE,
        filledIcon = Icons.Filled.Favorite,
        outlinedIcon = Icons.Outlined.FavoriteBorder
      ),
      RouteConfig(
        "Profile",
        PROFILE_SCREEN_ROUTE,
        filledIcon = Icons.Filled.Person,
        outlinedIcon = Icons.Outlined.Person
      )
    )
  }

}

data class RouteConfig(
  val label: String,
  val route: String,
  val filledIcon: ImageVector,
  val outlinedIcon: ImageVector
)
