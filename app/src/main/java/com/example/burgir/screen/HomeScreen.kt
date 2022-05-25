import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.burgir.R
import com.example.burgir.screen.CartScreen
import com.example.burgir.screen.ProfileScreen
import com.example.burgir.ui.theme.BurgirTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

  val navController = rememberNavController()

  Scaffold(
    modifier = modifier,
    topBar = {
      CenterAlignedTopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        actions = {
          IconButton(onClick = { navController.navigate("profile_screen") { popUpTo("menu_screen") } }) {
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
          navController.navigate("cart_screen") {
            popUpTo("menu_screen")
          }
        },
        text = { Text("Checkout") },
        icon = {
          Icon(
            imageVector = Icons.Filled.ShoppingCart,
            contentDescription = "Account Icon"
          )
        },
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary
      )
    },
    content = { innerPadding ->
      NavHost(
        navController = navController,
        startDestination = "menu_screen",
        modifier = modifier.padding(innerPadding)
      ) {
        composable("menu_screen") { MenuScreen() }
        composable("profile_screen") { ProfileScreen() }
        composable("cart_screen") { CartScreen() }
      }
    },
  )
}

@Composable
fun MenuScreen(modifier: Modifier = Modifier) {
  Column(modifier = modifier) {
    CategorySlider()
    Spacer(modifier = Modifier.size(30.dp))
    ProductsGrid()
  }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 600)
@Composable
fun ScreenPreview() {
  BurgirTheme {
    MenuScreen()
  }
}