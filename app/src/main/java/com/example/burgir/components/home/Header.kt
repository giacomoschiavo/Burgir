import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.burgir.ui.theme.AppTypography

@Composable
fun Header(chosenCategoryId: Int, setCategoryId: (Int) -> Unit, modifier: Modifier = Modifier) {
  Column(modifier = modifier) {
    Spacer(modifier = Modifier.size(10.dp))
    Text(text = "Hey Mike,", style = AppTypography.bodyLarge)
    Text(
      text = "Choose Your\nBest Meal",
      style = AppTypography.displayMedium.copy(fontWeight = FontWeight.ExtraBold)
    )
    CategorySlider(chosenCategoryId, setCategoryId)
    Text(
      text = "Popular",
      style = AppTypography.displaySmall.copy(fontWeight = FontWeight.Bold),
      modifier = Modifier.padding(vertical = 10.dp)
    )
    Spacer(modifier = Modifier.size(5.dp))
  }

}