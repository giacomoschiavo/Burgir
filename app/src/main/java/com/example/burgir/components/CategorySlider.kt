import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.burgir.ui.theme.AppTypography
import com.example.burgir.ui.theme.BurgirTheme

// TODO: solve this
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Category(
  category: CategoryUiState,
  isChosen: Boolean,
  onCategoryClicked: (Int) -> Unit,
  modifier: Modifier = Modifier
) {

  val backgroundColor by animateColorAsState(
    targetValue = if (isChosen) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background,
    animationSpec = tween(durationMillis = 50, easing = FastOutSlowInEasing)
  )
  val scale by animateFloatAsState(
    targetValue = if (isChosen) 1.09f else 1.0f,
    animationSpec = spring(
      dampingRatio = Spring.DampingRatioHighBouncy,
      stiffness = Spring.StiffnessLow
    )
  )

  ElevatedCard(
    onClick = { onCategoryClicked(category.id) },
    modifier = modifier.scale(scale),
    colors = CardDefaults.elevatedCardColors(containerColor = backgroundColor),
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier.padding(vertical = 6.dp, horizontal = 10.dp),
    ) {
      Image(
        painter = painterResource(category.imageRes),
        contentDescription = null,
        modifier = Modifier
          .size(45.dp)
          .padding(5.dp)
      )
      Text(
        text = category.name,
      )
    }
  }
}


@Composable
fun CategorySlider(chosenCategoryId: Int, setChosenCategoryId: (Int) -> Unit, modifier: Modifier = Modifier) {
  LazyRow(
    modifier = modifier.padding(top = 10.dp),
    contentPadding = PaddingValues(horizontal = 16.dp),
    horizontalArrangement = Arrangement.spacedBy(20.dp),
  ) {
    items(categories) { category ->
      Category(category, category.id == chosenCategoryId, setChosenCategoryId)
    }
  }
}

@Preview(showBackground = true)
@Composable
fun CategoryPreview() {
  BurgirTheme() {
    CategorySlider(0, {})
  }
}