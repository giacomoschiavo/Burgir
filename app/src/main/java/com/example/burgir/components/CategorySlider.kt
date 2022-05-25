import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.burgir.R
import com.example.burgir.ui.theme.BurgirTheme

// TODO: solve this
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Category(category: CategoryUiState, modifier: Modifier = Modifier) {
  var clicked by rememberSaveable { mutableStateOf(false) }
  val backgroundColor by animateColorAsState( if (clicked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background)
  val scale by animateFloatAsState(if (clicked) 1.07f else 1.0f)

  ElevatedCard(
    onClick = { clicked = !clicked },
    modifier = modifier.scale(scale),
    colors = CardDefaults.elevatedCardColors(containerColor = backgroundColor)
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier.padding(6.dp),
    ) {
      Image(
        painter = painterResource(category.imageRes),
        contentDescription = null,
        modifier = Modifier
          .size(90.dp)
          .padding(5.dp)
      )
      Text(
        text = category.name,
//        style = MaterialTheme.typography.h6,
      )
    }
  }
}


@Composable
fun CategorySlider(modifier: Modifier = Modifier) {
  LazyRow(
    modifier = modifier.padding(top = 10.dp),
    contentPadding = PaddingValues(horizontal = 16.dp),
    horizontalArrangement = Arrangement.spacedBy(20.dp),
  ) {
    items(categories) { category ->
      Category(category, modifier)
    }
  }
}

@Preview(showBackground = true)
@Composable
fun CategoryPreview() {
  BurgirTheme() {
    CategorySlider()
  }
}