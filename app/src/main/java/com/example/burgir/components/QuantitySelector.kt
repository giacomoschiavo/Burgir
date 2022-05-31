import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.burgir.R

@Composable
fun QuantitySelector(modifier: Modifier = Modifier) {
  var quantity by rememberSaveable { mutableStateOf(1) }

  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceAround,
    modifier = modifier
  ) {
    IconButton(onClick = { quantity-- }) {
      Icon(
        painter = painterResource(id = R.drawable.ic_baseline_remove_24),
        contentDescription = null
      )
    }
    Text(
      text = quantity.toString(),
      modifier = Modifier
        .padding(10.dp),
      textAlign = TextAlign.Center
    )
    IconButton(onClick = { quantity++ }) {
      Icon(
        imageVector = Icons.Filled.Add,
        contentDescription = null
      )
    }
  }
}