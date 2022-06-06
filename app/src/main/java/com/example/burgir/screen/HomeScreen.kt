import androidx.compose.foundation.layout.PaddingValues
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
import androidx.navigation.compose.rememberNavController
import com.example.burgir.components.PrimaryScaffold
import com.example.burgir.navigation.AppState
import com.example.burgir.ui.theme.AppTypography

@Composable
fun HomeScreen(
  appState: AppState,
  modifier: Modifier = Modifier
) {
  var chosenCategoryId by rememberSaveable { mutableStateOf(0) }

  PrimaryScaffold(appState, modifier) { innerPadding ->
    LazyVerticalGrid(
      columns = GridCells.Fixed(2),
      contentPadding = PaddingValues(
        top = innerPadding.calculateTopPadding(),
        bottom = innerPadding.calculateBottomPadding(),
        start = 10.dp,
        end = 10.dp
      )
    ) {
      item(span = { GridItemSpan(2) }, key = 200) {
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
      item(key = 201) { Spacer(modifier = Modifier.size(10.dp)) }
      item(span = { GridItemSpan(2) }, key = 202) {
        Text(
          text = "Choose Your\nBest Meal",
          style = AppTypography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )
      }
      item(span = { GridItemSpan(2) }, key = 203) {
        CategorySlider(
          chosenCategoryId,
          { newCategoryId -> chosenCategoryId = newCategoryId },
        )
      }
      item(key = 204) { Spacer(modifier = Modifier.size(20.dp)) }
      item(span = { GridItemSpan(2) }, key = 205) {
        Text(
          text = "Popular",
          style = AppTypography.headlineMedium,
          modifier = Modifier.paddingFromBaseline(top = 10.dp)
        )
      }
      items(
        appState.products.filter { it.category == chosenCategoryId }
          .sortedByDescending { it.timesPurchased }
          .take(5),
        key = { product -> product.id }) { product ->
        ProductItem(product, appState.navigateToProduct)
      }
    }
  }

}


@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun ScreenPreview() {
  HomeScreen(AppState(rememberNavController(), products))
}