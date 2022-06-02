import androidx.compose.foundation.layout.Spacer
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
  popularProducts: (List<Product>),
  modifier: Modifier = Modifier
) {
  var chosenCategoryId by rememberSaveable { mutableStateOf(0) }

  LazyVerticalGrid(
    columns = GridCells.Fixed(2),
  ) {
    item(span = { GridItemSpan(2) }, key = "profile name") {
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
    item(key = "spacer0") { Spacer(modifier = Modifier.size(10.dp)) }
    item(span = { GridItemSpan(2) }, key = "choose") {
      Text(
        text = "Choose Your\nBest Meal",
        style = AppTypography.displayMedium.copy(fontWeight = FontWeight.ExtraBold)
      )
    }
    item(span = { GridItemSpan(2) }, key = "category slider") {
      CategorySlider(
        chosenCategoryId,
        { newCategoryId -> chosenCategoryId = newCategoryId },
      )
    }
    item(key = "spacer1") { Spacer(modifier = Modifier.size(20.dp)) }
    item(span = { GridItemSpan(2) }, key = "popular") {
      Text(
        text = "Popular",
        style = AppTypography.headlineMedium.copy(fontWeight = FontWeight.Bold),
        modifier = Modifier.paddingFromBaseline(top = 10.dp)
      )
    }
    items(
      popularProducts,
      key = { product -> product.id }) { product ->
      ProductItem(product, navigateToProduct, modifier)
    }
  }
}


@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun ScreenPreview() {
  BurgirTheme {
    HomeScreen({ }, listOf())
  }
}