package com.example.burgir.components.cart

import PriceLabel
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.burgir.R
import com.example.burgir.ui.theme.AppTypography

/*
In questo componente vengono visualizzati tutti i prezzi: originale, sconto e prezzo scontato
E' presente il button per mostrare il dialog di conferma
 */

@Composable
fun PaymentSummary(
  originalPrice: Double,
  openDialog: () -> Unit,
  discount: Double,
  modifier: Modifier = Modifier
) {

  Column(modifier = modifier) {
    Column(
      verticalArrangement = Arrangement.SpaceAround,
      modifier = Modifier
        .weight(1f)
        .padding(vertical = 5.dp)
    ) {
      // mostra i 3 prezzi
      PaymentSummaryItem(stringResource(R.string.cart_screen_items), originalPrice)
      PaymentSummaryItem(stringResource(R.string.cart_screen_discount), discount)
      PaymentSummaryItem(stringResource(R.string.cart_screen_cost), originalPrice - discount)
    }
    // apre il dialog di conferma
    Button(
      onClick = openDialog, modifier = Modifier
        .fillMaxWidth()
        .weight(0.3f)
    ) {
      Text(text = stringResource(R.string.cart_screen_checkout_button_text))
      Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = null)
    }
  }
}

@Composable
fun PaymentSummaryItem(key: String, value: Double) {
  Row() {
    Text(text = key, modifier = Modifier.weight(1f), style = AppTypography.titleMedium)
    PriceLabel(price = value, style = AppTypography.titleMedium)
  }
}

@Preview(showBackground = true, heightDp = 150)
@Composable
fun PaymentSummaryPreview() {
  PaymentSummary(discount = 12.0, originalPrice = 12.34, openDialog = {})
}