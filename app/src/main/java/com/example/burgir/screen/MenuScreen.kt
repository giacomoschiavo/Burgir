import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.burgir.MainActivity
import com.example.burgir.R
import com.example.burgir.data.CartViewModel
import com.example.burgir.screen.CartScreen
import com.example.burgir.screen.ProductScreen
import com.example.burgir.screen.ProfileScreen
import com.example.burgir.screen.SplashScreen
import com.example.burgir.ui.theme.BurgirTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
  navController: NavController,
  modifier: Modifier = Modifier
) {

  val navControllerMenu = rememberNavController()

  Scaffold(
    modifier = modifier,
    topBar = {
      CenterAlignedTopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        actions = {
          IconButton(onClick = {
            navController.navigate(MainActivity.PROFILE_SCREEN_ROUTE) {
              popUpTo(
                MainActivity.MENU_SCREEN_ROUTE
              )
            }
          }) {
            Icon(
              imageVector = Icons.Filled.AccountCircle,
              contentDescription = "Account Icon"
            )
          }
        },
      )
    },
    floatingActionButton = {
      ExtendedFloatingActionButton(
        onClick = {
          navController.navigate(MainActivity.CART_SCREEN_ROUTE) {
            popUpTo(MainActivity.MENU_SCREEN_ROUTE)
          }
        },
        text = { Text("Checkout") },
        icon = {
          Icon(
            imageVector = Icons.Filled.ShoppingCart,
            contentDescription = "Shopping Cart Icon"
          )
        },
        modifier = Modifier,
        containerColor = MaterialTheme.colorScheme.primary
      )
    },
    content = { innerPadding ->
      NavHost(
        navController = navControllerMenu,
        startDestination = MainActivity.MENU_SCREEN_ROUTE,
        modifier = Modifier.padding(innerPadding)
      ) {
        composable(MainActivity.MENU_SCREEN_ROUTE) {
          MenuScreen({ productId ->
            navControllerMenu.navigate("${MainActivity.PRODUCT_SCREEN_ROUTE}/$productId") {
              popUpTo(
                MainActivity.MENU_SCREEN_ROUTE
              )
            }
          })
        }
        composable(MainActivity.PROFILE_SCREEN_ROUTE) { ProfileScreen() }
        composable(MainActivity.CART_SCREEN_ROUTE) { CartScreen() }
        composable("${MainActivity.PRODUCT_SCREEN_ROUTE}/{productId}") { backStackEntry ->
          ProductScreen(backStackEntry.arguments?.getString("productId")!!.toInt())
        }
      }
    },
  )
}

@Composable
fun MenuScreen(handleProductIdNavigation: (Int) -> Unit, modifier: Modifier = Modifier) {

  var chosenCategoryId by rememberSaveable { mutableStateOf(0) }

  Column(modifier = modifier) {
    CategorySlider(
      chosenCategoryId,
      { newCategoryId -> chosenCategoryId = newCategoryId }
    )
    Spacer(modifier = Modifier.size(30.dp))
    ProductsGrid(chosenCategoryId, handleProductIdNavigation)
  }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 600)
@Composable
fun ScreenPreview() {
  BurgirTheme {
    MenuScreen({})
  }
}