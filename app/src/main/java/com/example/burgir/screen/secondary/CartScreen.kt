package com.example.burgir.screen.secondary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.burgir.R
import com.example.burgir.components.SecondaryScaffold
import com.example.burgir.components.cart.CartItem
import com.example.burgir.components.cart.ConfirmDialog
import com.example.burgir.components.cart.PaymentSummary
import com.example.burgir.data.BurgirViewModel

/*
Schermata del carrello
Contiene tutti i prodotti che sono stati aggiunti al carrello attraverso la schermata di descrizione
del prodotto (ProductDetailsScreen)
Viene indicato il prezzo originale, lo sconto e il prezzo finale.
Il pulsante di "Payment & Checkout" attiva un dialog per confermare il carrello (ed il pagamento).
Si presuppone che premendo "Confirm" sul dialog il pagamento viene effettuato correttamente.
Una volta che il pagamento avviene con successo, il carrelo viene svuotato.
Tutti i prodotti acquistati vengono salvati su database per ottenere i prodotti piu acquistati
dall'utente
 */

@Composable
fun CartScreen(
  navController: NavController,
  burgirViewModel: BurgirViewModel
) {

  // ottieni i prodotti del carrello
  burgirViewModel.getProductsinCart()
  // osserva i prodotti nel carrello
  val productsOnCart by burgirViewModel.products.observeAsState(emptyList())

  // true: apre il dialog, false: chiude il dialog
  var openDialog by remember { mutableStateOf(false) }

  SecondaryScaffold(
    navController = navController,
    burgirViewModel = burgirViewModel,
    title = stringResource(R.string.cart_screen_headline),
    content = { innerPadding ->
      Column(modifier = Modifier.padding(innerPadding)) {
        // la lista dei prodotti e' scrollabile
        LazyColumn(
          modifier = Modifier.weight(0.8f),
          contentPadding = PaddingValues(vertical = 10.dp),
          horizontalAlignment = Alignment.CenterHorizontally,
        ) {
          // disegna ogni prodotto del carrello
          items(productsOnCart, key = { product -> product.id }) { product ->
            CartItem(
              product = product,
              modifier = Modifier.padding(10.dp),
              onAdd = { burgirViewModel.addToCart(product.id) },
              onRemove = { burgirViewModel.removeFromCart(product.id) },
              removeAll = { burgirViewModel.removeAllFromCartByProductId(product.id) }
            )
            Divider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))
          }
        }

        val originalPrice = productsOnCart.sumOf { it.productPrice * it.cartQuantity }
        val discount =
          productsOnCart.sumOf { (it.productPrice / 100 * it.discount) * it.cartQuantity }
        PaymentSummary(
          originalPrice = originalPrice,
          discount = discount,
          modifier = Modifier
            .weight(0.3f)
            .padding(15.dp),
          openDialog = {
            if (originalPrice > 0) openDialog = true
          } // apri se il carrello non e'vuoto
        )
        ConfirmDialog(
          isOpen = openDialog,
          closeDialog = { openDialog = false },
          onConfirm = {
            if (originalPrice > 0) burgirViewModel.checkout(originalPrice - discount)
            openDialog = false
          })
      }
    })
}
