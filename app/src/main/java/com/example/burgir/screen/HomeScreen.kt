import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.burgir.MainActivity
import com.example.burgir.R
import com.example.burgir.ui.theme.AppTypography
import com.example.burgir.ui.theme.BurgirTheme

@Composable
fun HomeScreen(
  navigateToProduct: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  var chosenCategoryId by rememberSaveable { mutableStateOf(0) }

  LazyVerticalGrid(
    columns = GridCells.Fixed(2),
  ) {
    item(span = { GridItemSpan(2) }) {
      Text(
        text = buildAnnotatedString {
          append("Hey, ")
          withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("Mike")
          }
        },
        style = AppTypography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
      )
    }
    item() { Spacer(modifier = Modifier.size(10.dp)) }
    item(span = { GridItemSpan(2) }) {
      Text(
        text = "Choose Your\nBest Meal",
        style = AppTypography.displayMedium.copy(fontWeight = FontWeight.ExtraBold)
      )
    }
    item() { Spacer(modifier = Modifier.size(30.dp)) }
    item(span = { GridItemSpan(2) }) {
      CategorySlider(
        chosenCategoryId,
        { newCategoryId -> chosenCategoryId = newCategoryId },
      )
    }
    item() { Spacer(modifier = Modifier.size(30.dp)) }
    item(span = { GridItemSpan(2) }) {
      Text(
        text = "Popular",
        style = AppTypography.headlineMedium.copy(fontWeight = FontWeight.Bold),
        modifier = Modifier.paddingFromBaseline(top = 10.dp)
      )
    }
    items(
      products.filter { chosenCategoryId == it.categoryId },
      key = { product -> product.id }) { product ->
      ProductItem(product, navigateToProduct, modifier)
    }
  }
}


@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun ScreenPreview() {
  BurgirTheme {
    HomeScreen({ })
  }
}