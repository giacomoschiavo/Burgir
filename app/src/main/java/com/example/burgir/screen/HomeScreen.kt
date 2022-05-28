import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
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
import androidx.navigation.compose.rememberNavController
import com.example.burgir.MainActivity
import com.example.burgir.R
import com.example.burgir.ui.theme.BurgirTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  navController: NavController,
  modifier: Modifier = Modifier
) {
  var chosenCategoryId by rememberSaveable { mutableStateOf(0) }

  Scaffold(
    modifier = modifier,
    topBar = { MenuTopAppBar(navController) },
//    floatingActionButton = {
//      ExtendedFloatingActionButton(
//        onClick = {
//          navController.navigate(MainActivity.CART_SCREEN_ROUTE) {
//            popUpTo(MainActivity.MENU_SCREEN_ROUTE)
//          }
//        },
//        text = { Text("Checkout") },
//        icon = {
//          Icon(
//            imageVector = Icons.Filled.ShoppingCart,
//            contentDescription = "Shopping Cart Icon"
//          )
//        },
//        modifier = Modifier,
//        containerColor = MaterialTheme.colorScheme.primary
//      )
//    },
    bottomBar = { BottomBar(navController) }
  ) { innerPadding ->
    Column(modifier = Modifier.padding(innerPadding)) {
      CategorySlider(
        chosenCategoryId,
        { newCategoryId -> chosenCategoryId = newCategoryId }
      )
      Spacer(modifier = Modifier.size(30.dp))
      ProductsGrid(
        chosenCategoryId,
        { productId ->
          navController.navigate("${MainActivity.PRODUCT_SCREEN_ROUTE}/$productId") {
            popUpTo(MainActivity.MENU_SCREEN_ROUTE)
          }
        }
      )
    }
  }

}


@Preview(showBackground = true, widthDp = 320, heightDp = 600)
@Composable
fun ScreenPreview() {
  BurgirTheme {
    HomeScreen(rememberNavController())
  }
}