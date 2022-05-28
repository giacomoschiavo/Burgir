import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.burgir.MainActivity
import com.example.burgir.R
import com.example.burgir.ui.theme.AppTypography
import com.example.burgir.ui.theme.BurgirTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  navController: NavController,
  modifier: Modifier = Modifier
) {
  var chosenCategoryId by rememberSaveable { mutableStateOf(0) }

  Scaffold(
    topBar = { MenuTopAppBar(navController) },
    bottomBar = { BottomBar(navController) },
  ) { innerPadding ->
    Column(
      modifier = Modifier.padding(
        vertical = innerPadding.calculateTopPadding(),
        horizontal = 15.dp
      )
    ) {
      Spacer(modifier = Modifier.size(10.dp))
      Text(text = "Hey Mike,", style = AppTypography.bodyLarge)
      Text(text = "Choose Your\nBest Meal", style = AppTypography.displayMedium)
      CategorySlider(
        chosenCategoryId,
        { newCategoryId -> chosenCategoryId = newCategoryId }
      )
      Text(
        text = "Popular",
        style = AppTypography.displaySmall,
        modifier = Modifier.padding(vertical = 10.dp)
      )
      Spacer(modifier = Modifier.size(5.dp))
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