import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.burgir.data.Product
import com.example.burgir.navigation.AppState

@Composable
fun ProductsGrid(
  products: List<Product>,
  navigateToProduct: (Int) -> Unit,
  modifier: Modifier = Modifier,
  header: @Composable () -> Unit = {},
  appState: AppState
) {
  LazyVerticalGrid(
    columns = GridCells.Fixed(2),
    modifier = modifier
  ) {
    item(span = { GridItemSpan(2) }) { header() }
    items(products, key = { product -> product.id }) { product ->
      ProductItem(product, navigateToProduct)
    }
  }
}


@Preview(showBackground = true)
@Composable
fun ProductPreview() {
//    ProductItem(products[0], {})
}

@Preview(showBackground = true)
@Composable
fun ProductsGridPreview() {
//    ProductsGrid(
//      products,
//      header = { Text("An Amazing Title") },
//      modifier = Modifier,
//      navigateToProduct = {})
}


