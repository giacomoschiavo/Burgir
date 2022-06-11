import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.burgir.data.Category
import com.example.burgir.ui.theme.BurgirTheme


@Composable
fun CategorySlider(
  chosenCategoryId: Int,
  setChosenCategoryId: (Int) -> Unit,
  categories: List<Category>,
  modifier: Modifier = Modifier
) {
  LazyRow(
    modifier = modifier.padding(top = 10.dp),
    horizontalArrangement = Arrangement.spacedBy(20.dp),
    contentPadding = PaddingValues(20.dp),
  ) {
    items(categories) { category ->
      Category(category, isChosen = category.id == chosenCategoryId, setChosenCategoryId)
    }
  }
}

@Preview(showBackground = true)
@Composable
fun CategoryPreview() {
  BurgirTheme() {
//    CategorySlider(0, {})
  }
}