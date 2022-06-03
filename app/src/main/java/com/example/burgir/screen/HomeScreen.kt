import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.burgir.data.Product
import com.example.burgir.ui.theme.AppTypography
import com.example.burgir.ui.theme.BurgirTheme

@Composable
fun HomeScreen(
  navigateToProduct: (Int) -> Unit,
  products: List<Product>,
  modifier: Modifier = Modifier
) {
  var chosenCategoryId by rememberSaveable { mutableStateOf(0) }

  LazyVerticalGrid(
    columns = GridCells.Fixed(2),
  ) {
    item(span = { GridItemSpan(2) }, key = 123) {
      Text(
        text = buildAnnotatedString {
          append("Hey, ")
          withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("Mike")
          }
        },
        style = AppTypography.titleLarge,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
      )
    }
    item(key = 124) { Spacer(modifier = Modifier.size(10.dp)) }
    item(span = { GridItemSpan(2) }, key = 125) {
      Text(
        text = "Choose Your\nBest Meal",
        style = AppTypography.displayMedium.copy(fontWeight = FontWeight.ExtraBold)
      )
    }
    item(span = { GridItemSpan(2) }, key = 126) {
      CategorySlider(
        chosenCategoryId,
        { newCategoryId -> chosenCategoryId = newCategoryId },
      )
    }
    item(key = 127) { Spacer(modifier = Modifier.size(20.dp)) }
    item(span = { GridItemSpan(2) }, key = 128) {
      Text(
        text = "Popular",
        style = AppTypography.headlineMedium,
        modifier = Modifier.paddingFromBaseline(top = 10.dp)
      )
    }
    items(
      products.filter { it.category == chosenCategoryId }.sortedByDescending { it.timesPurchased }
        .take(5),
      key = { product -> product.id }) { product ->
      ProductItem(product, navigateToProduct)
    }
  }
}


@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun ScreenPreview() {
  BurgirTheme {
    HomeScreen({ }, products)
  }
}