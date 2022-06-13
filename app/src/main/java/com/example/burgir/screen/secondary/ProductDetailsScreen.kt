package com.example.burgir.screen.secondary

import ProductDescription
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.burgir.R
import com.example.burgir.components.SecondaryScaffold
import com.example.burgir.data.BurgirViewModel

/*
Mostra tutte le informazioni riguardanti un determinato prodotto.
Il prodotto in questione e' specificato dall'id passato come parametro di navigazione.
Il prodotto viene estratto dalla lista dei prodotti
Questa e' l'unica schermata in cui e' possibile aggiungere un prodotto al carrello
 */

@Composable
fun ProductDetailsScreen(
  navController: NavController,
  productId: Int,
  modifier: Modifier = Modifier,
  burgirViewModel: BurgirViewModel
) {

  // id non riconosciuto
  if (productId == -1) {
    Text(text = stringResource(id = R.string.err_product_not_found))
    return
  }

  // ottieni il prodotto
  val product by burgirViewModel.getProductById(productId).collectAsState(null)
  if (product == null) {
    Text(text = stringResource(id = R.string.err_product_not_found))
    return
  }

  SecondaryScaffold(
    navController = navController,
    burgirViewModel = burgirViewModel,
    product = product,
    showFavoriteIcon = true,
    showCartIcon = true,
    title = product?.productName ?: "",
    onFavoriteClick = { product -> burgirViewModel.updateFavorite(product.id) },
    content = { innerPadding ->
      // crea colonna scrollabile
      LazyColumn(modifier = modifier.padding(innerPadding)) {
        // immagine del prodotto
        item {
          Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
            if (product != null) {
              Image(
                modifier = Modifier
                  .size(250.dp),
                painter = painterResource(id = product!!.imageUrl),
                contentDescription = stringResource(id = R.string.acc_product_image)
              )
            }
          }
        }
        // descrizione (nome, prezzo, carrello)
        item {
          ProductDescription(product, onAddToCart = { productId, quantity ->
            repeat(quantity) {
              burgirViewModel.addToCart(productId)
            }
          })
        }
      }
    }
  )
}