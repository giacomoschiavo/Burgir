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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.burgir.components.SecondaryScaffold
import com.example.burgir.data.BurgirViewModel

@Composable
fun ProductDetailsScreen(
  navController: NavController,
  productId: Int,
  modifier: Modifier = Modifier,
  burgirViewModel: BurgirViewModel
) {

  if (productId == -1) {
    Text(text = "Product not found!")
    return
  }

  burgirViewModel.getAllProducts()
  val products by burgirViewModel.products.observeAsState()
  val product = products?.find { product -> product.id == productId }

  SecondaryScaffold(
    navController = navController,
    burgirViewModel = burgirViewModel,
    showFavoriteIcon = true,
    showCartIcon = true,
    title = product?.productName ?: "",
    onFavoriteClick = { product ->
      burgirViewModel.updateFavorite(product.id)
    },
    product = product,
    content = { innerPadding ->
      LazyColumn(modifier = modifier.padding(innerPadding)) {
        item {
          Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
            if (product != null) {
              Image(
                modifier = Modifier
                  .size(220.dp),
                painter = painterResource(id = product.imageUrl),
                contentDescription = "image of the product"
              )
            }
          }
        }
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

@Preview(showBackground = true)
@Composable
fun ProductScreenPreview() {
//    ProductDetailsScreen(0, products = products)
}