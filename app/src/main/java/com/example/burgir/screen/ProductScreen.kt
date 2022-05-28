package com.example.burgir.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
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
import com.example.burgir.R
import com.example.burgir.ui.theme.AppTypography
import com.example.burgir.ui.theme.BurgirTheme
import products

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(navController: NavController, productId: Int, modifier: Modifier = Modifier) {

  val product = products.find { product -> product.id == productId }
  var quantity by remember { mutableStateOf(0) }

  androidx.compose.material3.Scaffold(
    modifier = modifier.fillMaxSize(),
    topBar = {
      CenterAlignedTopAppBar(
        modifier = Modifier,
        title = { Text(text = "Product Details", style = AppTypography.titleLarge) },
        navigationIcon = {
          IconButton(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back arrow")
          }
        }
      )
    }
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(PaddingValues(vertical = innerPadding.calculateTopPadding(), horizontal = 20.dp)),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      ElevatedCard(
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
      ) {
        Text(
          text = product!!.name,
          style = AppTypography.titleLarge,
          modifier = Modifier.padding(8.dp)
        )
      }
      Image(
        modifier = Modifier
          .padding(20.dp)
          .size(220.dp),
        painter = painterResource(id = product!!.imageUrl),
        contentDescription = "image of the product"
      )
      Text(text = product.description, modifier = Modifier.padding(vertical = 10.dp))

      AnimatedVisibility(visible = quantity < 1) {
        ElevatedButton(
          onClick = { quantity++ },
          modifier = Modifier.padding(32.dp)
        ) {
          Text(text = "Aggiungi al carrello", style = AppTypography.labelLarge)
        }
      }
      AnimatedVisibility(visible = quantity >= 1) {
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier.padding(innerPadding)
        ) {
          Text(text = "Quantita")
          Row(
            verticalAlignment = Alignment.CenterVertically
          ) {
            IconButton(onClick = { quantity-- }) {
              Icon(
                painter = painterResource(id = R.drawable.ic_baseline_remove_24),
                contentDescription = null

              )
            }
            Text(text = quantity.toString())
            IconButton(onClick = { quantity++ }) {
              Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null
              )
            }
          }
        }
      }
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