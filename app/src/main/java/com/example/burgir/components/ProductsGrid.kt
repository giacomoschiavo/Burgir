import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.burgir.data.Product

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
      products = products)
}


