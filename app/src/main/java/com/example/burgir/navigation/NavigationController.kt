import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.burgir.data.BurgirViewModel
import com.example.burgir.navigation.AppState
import com.example.burgir.screen.CartScreen
import com.example.burgir.screen.ProductDetailsScreen
import com.example.burgir.screen.ProfileScreen
import com.example.burgir.screen.SplashScreen

@Composable
fun NavigationController(burgirViewModel: BurgirViewModel = viewModel()) {

  val navController = rememberNavController()


  val products by burgirViewModel.products.observeAsState(emptyList())
  val categories by burgirViewModel.categories.observeAsState(emptyList())
  val appState =
    AppState(navController = navController, products = products, categories = categories)

  burgirViewModel.getAllProducts()

  NavHost(
    navController = navController,
    startDestination = AppState.SPLASH_SCREEN_ROUTE,
  ) {
    composable(AppState.SPLASH_SCREEN_ROUTE) { SplashScreen(navController) }
    composable(AppState.MENU_SCREEN_ROUTE) {
      HomeScreen(
        appState,
        burgirViewModel = burgirViewModel
      )
    }
    composable(AppState.PROFILE_SCREEN_ROUTE) {
      ProfileScreen(
        appState,
        burgirViewModel = burgirViewModel
      )
    }
    composable(AppState.FAVORITE_SCREEN_ROUTE) {
      FavoriteScreen(
        appState,
        burgirViewModel = burgirViewModel
      )
    }
    composable(AppState.CART_SCREEN_ROUTE) {
      CartScreen(
        navController = navController,
        products = products,
        appState = appState,
        burgirViewModel = burgirViewModel
      )
    }
    composable(AppState.SEARCH_SCREEN_ROUTE) { SearchScreen(appState) }
    composable("${AppState.PRODUCT_SCREEN_ROUTE}/{productId}") { backStackEntry ->
      ProductDetailsScreen(
        productId = backStackEntry.arguments?.getString("productId")?.toInt() ?: -1,
        products = products,
        appState = appState,
        burgirViewModel = burgirViewModel
      )
    }
    composable("${AppState.CATEGORY_SCREEN_ROUTE}/{categoryId}") { backStackEntry ->
      CategoryScreen(
        categoryId = backStackEntry.arguments?.getString("categoryId")!!.toInt(),
        navigateToProduct = appState.navigateToProduct,
        products = products,
        appState = appState,
        burgirViewModel = burgirViewModel
      )
    }
  }
}

