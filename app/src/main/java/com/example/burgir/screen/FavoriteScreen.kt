import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.burgir.components.PrimaryScaffold
import com.example.burgir.data.BurgirViewModel
import com.example.burgir.ui.theme.AppTypography

@Composable
fun FavoriteScreen(
  navController: NavController,
  burgirViewModel: BurgirViewModel,
) {

  burgirViewModel.getProductsByFavorite()
  val favoriteProducts by burgirViewModel.products.observeAsState(emptyList())

  PrimaryScaffold(
    navController = navController,
    burgirViewModel = burgirViewModel,
  ) { innerPadding ->
    ProductsGrid(
      navController = navController,
      products = favoriteProducts,
      header = {
        Text(
          text = "Your Favorites♥",
          textAlign = TextAlign.Center,
          style = AppTypography.displaySmall.copy(fontWeight = FontWeight.Bold),
          modifier = Modifier.paddingFromBaseline(bottom = 15.dp)
        )
      },
      modifier = Modifier.padding(innerPadding)
    )
  }

}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
  FavoriteScreen(rememberNavController(), viewModel())
}