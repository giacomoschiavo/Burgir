import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.burgir.MainActivity
import com.example.burgir.ui.theme.AppTypography
import com.example.burgir.ui.theme.BurgirTheme

@Composable
fun FavoriteScreen(navController: NavController, modifier: Modifier = Modifier) {
  ProductsGrid(
    title = {
      Text(
        text = "Your Favorites♥",
        textAlign = TextAlign.Center,
        style = AppTypography.displaySmall.copy(fontWeight = FontWeight.Medium),
        modifier = modifier.paddingFromBaseline(bottom = 15.dp)
      )
    },
    products = products.filter { product -> product.isFavorite },
    navigateToProduct = { productId ->
      navController.navigate("${MainActivity.PRODUCT_SCREEN_ROUTE}/$productId") {
        popUpTo(MainActivity.SEARCH_SCREEN_ROUTE)
      }
    }
  )
}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
  BurgirTheme() {
    FavoriteScreen(navController = rememberNavController())
  }
}