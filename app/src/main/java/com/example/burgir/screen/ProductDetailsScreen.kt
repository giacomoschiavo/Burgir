package com.example.burgir.screen

import ProductDescription
import ProductTopBar
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
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
import androidx.compose.ui.draw.shadow
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

@Composable
fun ProductDetailsScreen(
  productId: Int,
  modifier: Modifier = Modifier
) {
  val product = products.find { product -> product.id == productId }
  Column(modifier = modifier) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
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

@Preview(showBackground = true)
@Composable
fun ProductScreenPreview() {
  BurgirTheme() {
    ProductDetailsScreen(0)
  }
}