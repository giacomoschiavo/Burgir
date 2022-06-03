package com.example.burgir.screen

import ProductDescription
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.burgir.data.Product
import com.example.burgir.ui.theme.BurgirTheme
import products

@Composable
fun ProductDetailsScreen(
  productId: Int,
  modifier: Modifier = Modifier,
  products: List<Product>,
) {

  val product = products.find { product -> product.id == productId }

  LazyColumn(modifier = modifier) {
    item {
      Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
        Image(
          modifier = Modifier
            .size(220.dp),
          painter = painterResource(id = product!!.imageUrl),
          contentDescription = "image of the product"
        )
      }
    }
    item { ProductDescription(product) }
    item { Text("Times Purchased: ${product!!.timesPurchased}") }
  }
}

@Preview(showBackground = true)
@Composable
fun ProductScreenPreview() {
  BurgirTheme() {
    ProductDetailsScreen(0, products = products)
  }
}