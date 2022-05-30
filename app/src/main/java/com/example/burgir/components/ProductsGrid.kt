import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.burgir.ui.theme.BurgirTheme

@Composable
fun ProductsGrid(
  products: List<Product>,
  navigateToProduct: (Int) -> Unit,
  modifier: Modifier = Modifier,
  header: @Composable() (() -> Unit) = {}
) {
  LazyVerticalGrid(
    columns = GridCells.Fixed(2),
  ) {
    item(span = { GridItemSpan(2) }) { header() }
    items(products, key = { product -> product.id }) { product ->
      ProductItem(product, navigateToProduct, modifier)
    }
  }
}


@Preview(showBackground = true)
@Composable
fun ProductPreview() {
  BurgirTheme {
    ProductItem(products[0], {})
  }
}

@Preview(showBackground = true)
@Composable
fun ProductsGridPreview() {
  BurgirTheme {
    ProductsGrid(
      products,
      header = { Text("An Amazing Title") },
      modifier = Modifier,
      navigateToProduct = {})
  }
}


