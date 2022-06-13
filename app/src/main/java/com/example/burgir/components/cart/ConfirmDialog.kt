package com.example.burgir.components.cart

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.burgir.R

/*
Dialog per confermare il pagamento dal carrello
Viene visualizzato in base al valore di <isOpen> e ha 2 metodi di callback in caso conferma o disdetta
 */
@Composable
fun ConfirmDialog(isOpen: Boolean, closeDialog: () -> Unit, onConfirm: () -> Unit) {
  if (isOpen) {
    AlertDialog(
      // click al di fuori del dialog
      onDismissRequest = closeDialog,
      title = {
        Text(text = stringResource(R.string.payment_dialog_title))
      },
      text = {
        Text(text = stringResource(R.string.payment_dialog_description))
      },
      // click su conferma
      confirmButton = {
        TextButton(
          onClick = onConfirm
        ) {
          Text(stringResource(R.string.payment_dialog_confirm))
        }
      },
      // click su disdetta
      dismissButton = {
        TextButton(
          onClick = closeDialog
        ) {
          Text(stringResource(R.string.payment_dialog_cancel))
        }
      }
    )
  }
}
