import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.burgir.data.BurgirViewModel
import com.example.burgir.navigation.RouteConfig
import com.example.burgir.screen.CartScreen
import com.example.burgir.screen.ProductDetailsScreen
import com.example.burgir.screen.ProfileScreen
import com.example.burgir.screen.SplashScreen

@Composable
fun NavigationController(burgirViewModel: BurgirViewModel = viewModel()) {

  val navController = rememberNavController()
  NavHost(
    navController = navController,
    startDestination = RouteConfig.SPLASH_SCREEN_ROUTE,
  ) {
    composable(RouteConfig.SPLASH_SCREEN_ROUTE) { SplashScreen(navController) }
    composable(RouteConfig.MENU_SCREEN_ROUTE) {
      HomeScreen(
        navController = navController,
        burgirViewModel = burgirViewModel
      )
    }
    composable(RouteConfig.PROFILE_SCREEN_ROUTE) {
      ProfileScreen(
        navController = navController,
        burgirViewModel = burgirViewModel
      )
    }
    composable(RouteConfig.FAVORITE_SCREEN_ROUTE) {
      FavoriteScreen(
        navController = navController,
        burgirViewModel = burgirViewModel
      )
    }
    composable(RouteConfig.CART_SCREEN_ROUTE) {
      CartScreen(
        navController = navController,
        burgirViewModel = burgirViewModel
      )
    }
    composable(RouteConfig.SEARCH_SCREEN_ROUTE) { SearchScreen(navController, burgirViewModel) }
    composable("${RouteConfig.PRODUCT_SCREEN_ROUTE}/{productId}") { backStackEntry ->
      ProductDetailsScreen(
        productId = backStackEntry.arguments?.getString("productId")?.toInt() ?: -1,
        navController = navController,
        burgirViewModel = burgirViewModel
      )
    }
    composable("${RouteConfig.CATEGORY_SCREEN_ROUTE}/{categoryId}") { backStackEntry ->
      CategoryScreen(
        categoryId = backStackEntry.arguments?.getString("categoryId")!!.toInt(),
        navController = navController,
        burgirViewModel = burgirViewModel
      )
    }
  }
}

