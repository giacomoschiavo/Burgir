import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.burgir.R
import com.example.burgir.ui.theme.BurgirTheme


@Composable
fun Product(product: Product, modifier: Modifier = Modifier) {
  Surface(
    modifier = modifier
      .heightIn(220.dp)
      .clickable { },
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Image(
        painter = painterResource(product.imageUrl),
        contentDescription = null,
        modifier = Modifier
          .size(120.dp)
          .padding(10.dp)
      )
      Text(product.name)
    }
  }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductsGrid(chosenCategoryId: Int, modifier: Modifier = Modifier) {
  CompositionLocalProvider(
    LocalOverScrollConfiguration provides null
  ) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = modifier) {
      items(products.filter {product -> product.categoryId == chosenCategoryId }) { product ->
        Product(product, modifier)
      }
    }
  }
}


@Preview(showBackground = true)
@Composable
fun ProductPreview() {
  BurgirTheme {
    Product(products[0])
  }
}

@Preview(showBackground = true)
@Composable
fun ProductsGridPreview() {
  BurgirTheme {
    ProductsGrid(0)
  }
}


