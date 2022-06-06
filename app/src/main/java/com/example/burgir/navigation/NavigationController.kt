import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.burgir.data.Product
import com.example.burgir.navigation.AppState
import com.example.burgir.screen.CartScreen
import com.example.burgir.screen.ProductDetailsScreen
import com.example.burgir.screen.ProfileScreen
import com.example.burgir.screen.SplashScreen

@Composable
fun NavigationController(products: List<Product>) {

  val navController = rememberNavController()
  val appState = AppState(navController, products)

  NavHost(
    navController = navController,
    startDestination = AppState.SPLASH_SCREEN_ROUTE,
  ) {
    composable(AppState.SPLASH_SCREEN_ROUTE) { SplashScreen(navController = navController) }
    composable(AppState.MENU_SCREEN_ROUTE) { HomeScreen(appState) }
    composable(AppState.PROFILE_SCREEN_ROUTE) { ProfileScreen(appState) }
    composable(AppState.FAVORITE_SCREEN_ROUTE) { FavoriteScreen(appState) }
    composable(AppState.CART_SCREEN_ROUTE) {
      CartScreen(
        navController = navController,
        products = products,
        appState = appState
      )
    }
    composable(AppState.SEARCH_SCREEN_ROUTE) { SearchScreen(appState) }
    composable("${AppState.PRODUCT_SCREEN_ROUTE}/{productId}") { backStackEntry ->
      ProductDetailsScreen(
        productId = backStackEntry.arguments?.getString("productId")!!.toInt(),
        products = products,
        appState = appState
      )
    }
    composable("${AppState.CATEGORY_SCREEN_ROUTE}/{categoryId}") { backStackEntry ->
      CategoryScreen(
        categoryId = backStackEntry.arguments?.getString("categoryId")!!.toInt(),
        navigateToProduct = appState.navigateToProduct,
        products = products,
        appState = appState
      )
    }
  }
}

