package com.example.burgir.components.cart

import PriceLabel
import QuantitySelector
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.burgir.R
import com.example.burgir.data.Product
import com.example.burgir.ui.theme.AppTypography

/*
Questo componente permette di gestire un prodotto nel carrello.
Attraverso il QuantitySelector e' possibile scegliere la quantita nel carrello, mentre con il cestino
si puo rimuovere interamente il prodotto.
Se il prodotto e' scontato verra' disegnato al prezzo originale
 */

@Composable
fun CartItem(
  product: Product,
  onAdd: (Int) -> Unit,
  onRemove: (Int) -> Unit,
  removeAll: () -> Unit,
  modifier: Modifier = Modifier
) {
  Row(
    horizontalArrangement = Arrangement.SpaceEvenly,
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
  ) {
    Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
      // immagine prodotto
      Image(
        painter = painterResource(id = product.imageUrl),
        contentDescription = stringResource(R.string.acc_product_image),
        modifier = Modifier
          .size(100.dp)
          .padding(10.dp)
      )
      Column(verticalArrangement = Arrangement.SpaceBetween) {
        // nome prodotto
        Text(
          text = product.productName,
          style = AppTypography.titleSmall.copy(fontWeight = FontWeight.Bold)
        )
        // selezionatore di quantita
        QuantitySelector(
          onAdd = onAdd,
          onRemove = onRemove,
          modifier = Modifier.padding(top = 10.dp),
          initialQuantity = product.cartQuantity
        )
      }
    }
    Column(
      modifier = Modifier
        .weight(0.4f),
      horizontalAlignment = Alignment.End
    ) {
      // icona cestino
      IconButton(onClick = removeAll) {
        Icon(
          imageVector = Icons.Outlined.Delete,
          contentDescription = stringResource(R.string.acc_delete_all_product),
          modifier = Modifier.padding(10.dp)
        )
      }
      // prezzo scontato (o originale se non e' presente lo sconto)
      PriceLabel(
        price = product.productPrice - (product.productPrice / 100 * product.discount),
        style = AppTypography.titleLarge
      )
      // mostra prezzo originale se scontato (il prezzo e' rimpicciolito e ha un colore meno marcato)
      if (product.discount > 0) {
        PriceLabel(
          price = product.productPrice,
          style = AppTypography.bodySmall.copy(textDecoration = TextDecoration.LineThrough),
          color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
        )
      }
    }
  }

}

@Preview(showBackground = true, heightDp = 120)
@Composable
fun CartItemPreview() {
  CartItem(
    Product(
      id = 0,
      productName = "Big Mac",
      imageUrl = R.drawable.b_bigmac,
      category = 1,
      discount = 10,
      isFavorite = true,
    ), onAdd = {}, onRemove = {}, removeAll = {})
}