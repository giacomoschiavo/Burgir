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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.burgir.R

/*
Questo componente permette di gestire le quantita di un prodotto (sia nel carrello che nella schermata
di descrizione)
Ha due metodi che (onAdd e onRemove) che vengono richiamati al click + e -
Ha un valore iniziale di default = 1
 */

@Composable
fun QuantitySelector(
  onAdd: (Int) -> Unit,
  onRemove: (Int) -> Unit,
  modifier: Modifier = Modifier,
  initialQuantity: Int = 1
) {
  // tiene conto del contatore (soppravvive alle configuration changes)
  var counter by rememberSaveable { mutableStateOf(initialQuantity) }

  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceAround,
    modifier = modifier
  ) {
    // counter non puo essere minore di 1
    IconButton(onClick = {
      if (counter > 1) counter--
      onRemove(counter)
    }) {
      Icon(
        painter = painterResource(id = R.drawable.ic_baseline_remove_24),
        contentDescription = null
      )
    }
    Text(
      text = counter.toString(),
      modifier = Modifier
        .padding(10.dp),
      textAlign = TextAlign.Center
    )
    IconButton(onClick = {
      counter++
      onAdd(counter)
    }) {
      Icon(
        imageVector = Icons.Filled.Add,
        contentDescription = null
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun QuantitySelectorPreview() {
  QuantitySelector({}, {})
}