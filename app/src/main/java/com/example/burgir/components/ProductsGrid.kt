import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.burgir.R
import com.example.burgir.ui.theme.BurgirTheme


@Composable
fun Product(product: ProductUiState, modifier: Modifier = Modifier) {
  Surface(
    modifier = modifier
      .heightIn(220.dp)
      .clickable { },
    border = BorderStroke(1.dp, MaterialTheme.colors.background)
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Image(
        painter = painterResource(product.imageUrl),
        contentDescription = null,
        modifier = Modifier
          .size(120.dp)
          .padding(10.dp))
      Text(product.name)
    }
  }
}


@Composable
fun ProductsGrid(modifier: Modifier = Modifier) {
  LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = modifier) {
    items(products) { product ->
      Product(product, modifier)
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
    ProductsGrid()
  }
}


