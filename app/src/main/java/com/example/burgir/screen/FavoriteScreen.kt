import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.burgir.MainActivity
import com.example.burgir.components.PrimaryScaffold
import com.example.burgir.navigation.AppState
import com.example.burgir.ui.theme.AppTypography

@Composable
fun FavoriteScreen(
  appState: AppState,
  modifier: Modifier = Modifier
) {

  PrimaryScaffold(appState = appState) { innerPadding ->

    ProductsGrid(
      products = appState.products.filter { it.isFavorite },
      navigateToProduct = { productId ->
        appState.navController.navigate("${MainActivity.PRODUCT_SCREEN_ROUTE}/$productId") {
          popUpTo(MainActivity.SEARCH_SCREEN_ROUTE)
        }
      },
      header = {
        Text(
          text = "Your Favorites♥",
          textAlign = TextAlign.Center,
          style = AppTypography.displaySmall.copy(fontWeight = FontWeight.Bold),
          modifier = modifier.paddingFromBaseline(bottom = 15.dp)
        )
      },
      appState = appState,
      modifier = Modifier.padding(innerPadding)
    )
  }

}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
//  BurgirTheme() {
//    FavoriteScreen(
//      navController = rememberNavController(),
//      products = products.filter { it.isFavorite })
//  }
}