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
import androidx.compose.ui.graphics.vector.ImageVector

/*
Questa classe contiene tutte le informazioni riguardanti le destinazioni.
Sono salvati: nome route, indirizzo route, icona filled(focus) e outline(unfocus)
 */
class RouteConfig {
  companion object routes {
    const val SPLASH_SCREEN_ROUTE = "splashScreen"
    const val MENU_SCREEN_ROUTE = "menuScreen"
    const val CART_SCREEN_ROUTE = "cartScreen"
    const val PROFILE_SCREEN_ROUTE = "profileScreen"
    const val PRODUCT_SCREEN_ROUTE = "productScreen"
    const val SEARCH_SCREEN_ROUTE = "searchScreen"
    const val FAVORITE_SCREEN_ROUTE = "favoriteScreen"
    const val CATEGORY_SCREEN_ROUTE = "categoryScreen"

    val routesInfo = listOf(
      RouteInfo(
        "Home",
        MENU_SCREEN_ROUTE,
        filledIcon = Icons.Filled.Home,
        outlinedIcon = Icons.Outlined.Home
      ),
      RouteInfo(
        "Search",
        SEARCH_SCREEN_ROUTE,
        filledIcon = Icons.Filled.Search,
        outlinedIcon = Icons.Outlined.Search
      ),
      RouteInfo(
        "Favorite",
        FAVORITE_SCREEN_ROUTE,
        filledIcon = Icons.Filled.Favorite,
        outlinedIcon = Icons.Outlined.FavoriteBorder
      ),
      RouteInfo(
        "Profile",
        PROFILE_SCREEN_ROUTE,
        filledIcon = Icons.Filled.Person,
        outlinedIcon = Icons.Outlined.Person
      )
    )
  }

}

data class RouteInfo(
  val label: String,
  val route: String,
  val filledIcon: ImageVector,
  val outlinedIcon: ImageVector
)
