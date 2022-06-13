import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.burgir.data.Category

/*
Mostra lo slider con la selezione delle singole categorie.
Questo componente viene utilizzato nella HomeScreen
 */

@Composable
fun CategorySlider(
  chosenCategoryId: Int,
  setChosenCategoryId: (Int) -> Unit,
  categories: List<Category>,
  modifier: Modifier = Modifier
) {
  // scorrimento orizzontale
  LazyRow(
    modifier = modifier.padding(top = 10.dp),
    horizontalArrangement = Arrangement.spacedBy(20.dp),
    contentPadding = PaddingValues(20.dp),
  ) {
    // disegna ciascuna categoria
    items(categories) { category ->
      CategorySliderItem(category, isChosen = category.id == chosenCategoryId, setChosenCategoryId)
    }
  }
}

