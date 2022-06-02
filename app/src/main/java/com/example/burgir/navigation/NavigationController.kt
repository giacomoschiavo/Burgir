import android.content.res.Resources
import android.content.res.TypedArray
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.burgir.MainActivity
import com.example.burgir.R
import com.example.burgir.data.Product
import com.example.burgir.screen.CartScreen
import com.example.burgir.screen.ProductDetailsScreen
import com.example.burgir.screen.ProfileScreen
import com.example.burgir.screen.SplashScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationController(resources: Resources) {
  val burgerImageTypedArray: TypedArray = resources.obtainTypedArray(R.array.burgers)
  val burgerNamesTypedArray: TypedArray = resources.obtainTypedArray(R.array.burgers_names)
  val burgers = mutableListOf<Product>()
  (0 until burgerNamesTypedArray.length()).forEach {
    burgers.add(
      Product(
        id = it,
        imageUrl = burgerImageTypedArray.getResourceId(it, 0),
        productName = burgerNamesTypedArray.getString(it)!!,
        category = 0,
      )
    )
  }
  burgerImageTypedArray.recycle()
  burgerNamesTypedArray.recycle()

  val navController = rememberNavController()
  val backStackEntry by navController.currentBackStackEntryAsState()
  val currentRoute = backStackEntry?.destination?.route

  val navigateToProduct: (Int) -> Unit =
    { navController.navigate("${MainActivity.PRODUCT_SCREEN_ROUTE}/$it") { launchSingleTop } }
  val navigateToCategory: (Int) -> Unit =
    { navController.navigate("${MainActivity.CATEGORY_SCREEN_ROUTE}/$it") { launchSingleTop } }

  val decayAnimationSpec = rememberSplineBasedDecay<Float>()
  val scrollBehavior = remember(decayAnimationSpec) {
    TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
  }

  Scaffold(
    modifier = if (currentRoute?.substringBefore("/") == MainActivity.CATEGORY_SCREEN_ROUTE) Modifier.nestedScroll(
      scrollBehavior.nestedScrollConnection
    ) else Modifier,
    topBar = {
      when (currentRoute?.substringBefore("/")) {
        MainActivity.PRODUCT_SCREEN_ROUTE -> CustomTopBar(
          navController = navController,
          scrollBehavior = scrollBehavior
        )
        MainActivity.CATEGORY_SCREEN_ROUTE -> CustomTopBar(
          navController = navController,
          showFavoriteIcon = false,
          scrollBehavior = scrollBehavior,
          selectedCategoryId = backStackEntry?.arguments?.getString("categoryId")!!.toInt(),
        )
        MainActivity.SPLASH_SCREEN_ROUTE -> {}
        else -> LogoWithCartTopAppBar(navController)
      }
    },
    bottomBar = {
      when (currentRoute) {
        MainActivity.PRODUCT_SCREEN_ROUTE, MainActivity.SPLASH_SCREEN_ROUTE, MainActivity.CATEGORY_SCREEN_ROUTE, MainActivity.CART_SCREEN_ROUTE -> {}
        else -> MainNavigationBar(navController)
      }
    },
  ) { innerPadding ->
    NavHost(
      navController = navController,
      startDestination = MainActivity.SPLASH_SCREEN_ROUTE,
      modifier = Modifier
        .padding(innerPadding)
        .padding(horizontal = 15.dp),
    ) {
      composable(MainActivity.SPLASH_SCREEN_ROUTE) { SplashScreen(navController = navController) }
      composable(MainActivity.MENU_SCREEN_ROUTE) { HomeScreen(navigateToProduct, burgers) }
      composable(MainActivity.PROFILE_SCREEN_ROUTE) { ProfileScreen(navController = navController) }
      composable(MainActivity.FAVORITE_SCREEN_ROUTE) { FavoriteScreen(navController = navController) }
      composable(MainActivity.CART_SCREEN_ROUTE) { CartScreen(navController = navController) }
      composable(MainActivity.SEARCH_SCREEN_ROUTE) { SearchScreen(navigateToCategory = navigateToCategory) }
      composable("${MainActivity.PRODUCT_SCREEN_ROUTE}/{productId}") { backStackEntry ->
        ProductDetailsScreen(
          productId = backStackEntry.arguments?.getString("productId")!!.toInt()
        )
      }
      composable("${MainActivity.CATEGORY_SCREEN_ROUTE}/{categoryId}") { backStackEntry ->
        CategoryScreen(
          categoryId = backStackEntry.arguments?.getString("categoryId")!!.toInt(),
          navigateToProduct = navigateToProduct
        )
      }
    }
  }
}