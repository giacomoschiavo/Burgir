import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.burgir.MainActivity
import com.example.burgir.data.Product
import com.example.burgir.navigation.AppState
import com.example.burgir.screen.CartScreen
import com.example.burgir.screen.ProductDetailsScreen
import com.example.burgir.screen.ProfileScreen
import com.example.burgir.screen.SplashScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationController(products: List<Product>) {

  val navController = rememberNavController()
  val appState = AppState(navController, products)

  NavHost(
    navController = navController,
    startDestination = MainActivity.MENU_SCREEN_ROUTE,
    modifier = Modifier.padding(horizontal = 10.dp)
  ) {
    composable(MainActivity.SPLASH_SCREEN_ROUTE) { SplashScreen(navController = navController) }
    composable(MainActivity.MENU_SCREEN_ROUTE) { HomeScreen(appState) }
    composable(MainActivity.PROFILE_SCREEN_ROUTE) { ProfileScreen(appState) }
    composable(MainActivity.FAVORITE_SCREEN_ROUTE) { FavoriteScreen(appState) }
    composable(MainActivity.CART_SCREEN_ROUTE) {
      CartScreen(
        navController = navController,
        products = products,
        appState = appState
      )
    }
    composable(MainActivity.SEARCH_SCREEN_ROUTE) { SearchScreen(appState) }
    composable("${MainActivity.PRODUCT_SCREEN_ROUTE}/{productId}") { backStackEntry ->
      ProductDetailsScreen(
        productId = backStackEntry.arguments?.getString("productId")!!.toInt(),
        products = products,
        appState = appState
      )
    }
    composable("${MainActivity.CATEGORY_SCREEN_ROUTE}/{categoryId}") { backStackEntry ->
      CategoryScreen(
        categoryId = backStackEntry.arguments?.getString("categoryId")!!.toInt(),
        navigateToProduct = appState.navigateToProduct,
        products = products,
        appState = appState
      )
    }
  }
}

