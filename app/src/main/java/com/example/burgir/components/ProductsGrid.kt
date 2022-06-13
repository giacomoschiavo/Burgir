import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.burgir.R
import com.example.burgir.data.Product

/*
Questo componente renderizza i ProductItem in una lazy vertical grid
Accetta la lista dei prodotti come parametroe e anche un header opzionale.
 */

@Composable
fun ProductsGrid(
  navController: NavController,
  products: List<Product>,
  modifier: Modifier = Modifier,
  header: @Composable () -> Unit = {},
) {
  LazyVerticalGrid(
    columns = GridCells.Fixed(2),
    modifier = modifier,
    // gap margin
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    item(span = { GridItemSpan(2) }) { header() }
    items(products, key = { product -> product.id }) { product ->
      ProductItem(product, navController)
    }
  }
}


@Preview(showBackground = true)
@Composable
fun ProductsGridPreview() {
  ProductsGrid(
    navController = rememberNavController(),
    modifier = Modifier,
    products = listOf(
      Product(
        0,
        "Hamburger",
        2.50,
        20,
        category = 0,
        imageUrl = R.drawable.b_bigmac
      ),
      Product(
        1,
        "Hamburger",
        2.50,
        20,
        category = 0,
        imageUrl = R.drawable.b_bigmac
      ),
      Product(
        2,
        "Hamburger",
        2.50,
        20,
        category = 0,
        imageUrl = R.drawable.b_bigmac
      ),
      Product(
        3,
        "Hamburger",
        2.50,
        20,
        category = 0,
        imageUrl = R.drawable.b_bigmac
      ),
      Product(
        4,
        "Hamburger",
        2.50,
        20,
        category = 0,
        imageUrl = R.drawable.b_bigmac
      ),
    )
  )
}


