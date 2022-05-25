import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.burgir.R

// TODO: solve this
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Category(modifier: Modifier = Modifier) {
  var clicked by rememberSaveable { mutableStateOf(false) }
  Card(
    elevation = 5.dp,
    modifier = modifier,
    onClick = { clicked = !clicked },
    backgroundColor = if (clicked) MaterialTheme.colors.primary else MaterialTheme.colors.background
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier.padding(6.dp),
    ) {
      Image(
        painter = painterResource(R.drawable.burger),
        contentDescription = null,
        modifier = Modifier
          .size(90.dp)
          .padding(5.dp)
      )
      Text(
        text = "Burger",
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
    item() { Category() }
    item() { Category() }
    item() { Category() }
    item() { Category() }
    item() { Category() }
    item() { Category() }
    item() { Category() }
    item() { Category() }
  }
}