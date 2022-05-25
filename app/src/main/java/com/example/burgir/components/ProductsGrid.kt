import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
fun Product(modifier: Modifier = Modifier) {
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
        painter = painterResource(R.drawable.burger),
        contentDescription = null,
        modifier = Modifier
          .size(120.dp)
          .padding(10.dp))
      Text(text = "Burger super buono")
    }
  }
}


@Composable
fun ProductsGrid() {
  LazyVerticalGrid(columns = GridCells.Fixed(2)) {
    repeat(10, { item { Product() } })
  }
}


@Preview(showBackground = true)
@Composable
fun ProductPreview() {
  BurgirTheme {
    Product()
  }
}

@Preview(showBackground = true)
@Composable
fun ProductsGridPreview() {
  BurgirTheme {
    ProductsGrid()
  }
}


