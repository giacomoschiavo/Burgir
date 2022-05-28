package com.example.burgir.screen

import ProductDescription
import ProductTopBar
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.burgir.MainActivity
import com.example.burgir.R
import com.example.burgir.ui.theme.AppTypography
import com.example.burgir.ui.theme.BurgirTheme
import com.example.burgir.ui.theme.Shapes
import products

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(navController: NavController, productId: Int, modifier: Modifier = Modifier) {

  val product = products.find { product -> product.id == productId }

  androidx.compose.material3.Scaffold(
    modifier = modifier.fillMaxSize(),
    topBar = { ProductTopBar(navController) }
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(PaddingValues(vertical = innerPadding.calculateTopPadding(), horizontal = 20.dp)),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
          modifier = Modifier
            .padding(20.dp)
            .size(220.dp),
          painter = painterResource(id = product!!.imageUrl),
          contentDescription = "image of the product"
        )
      }
      ProductDescription(product!!)
    }
  }

}

@Preview(showBackground = true)
@Composable
fun ProductScreenPreview() {
  BurgirTheme() {
    ProductScreen(rememberNavController(), 0)
  }
}