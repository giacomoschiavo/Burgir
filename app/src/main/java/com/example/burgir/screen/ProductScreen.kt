package com.example.burgir.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import products

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(navController: NavController, productId: Int, modifier: Modifier = Modifier) {

  val product = products.find { product -> product.id == productId }

  val addedToCart = rememberSaveable { mutableStateOf(false) }

  androidx.compose.material3.Scaffold(
    modifier = modifier.fillMaxSize(),
    topBar = {
      CenterAlignedTopAppBar(
        modifier = Modifier,
        title = { Text(text = product!!.name, style = MaterialTheme.typography.bodyLarge) },
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
        .padding(innerPadding)
        .fillMaxSize(),
      horizontalAlignment = Alignment.Start
    ) {
      Image(
        modifier = Modifier
          .padding(20.dp)
          .size(220.dp),
        painter = painterResource(id = product!!.imageUrl),
        contentDescription = "image of the product"
      )
      Text(
        text = product.name,
        style = MaterialTheme.typography.displayMedium,
        modifier = Modifier.padding(8.dp)
      )
      Text(text = product.description)
      if (!addedToCart.value) {
        Button(
          colors = ButtonDefaults.buttonColors(),
          onClick = { addedToCart.value = true },
          modifier = Modifier.padding(32.dp)
        ) {
          Text(text = "Aggiungi al carrello")
        }
      } else {
        Column {
          Text(text = "Quantita")
          Row {
            IconButton(onClick = { /*TODO*/ }) {
              Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null
              )
            }
            Text(text = "1")
            IconButton(onClick = { /*TODO*/ }) {
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
  ProductScreen(rememberNavController(), 0)
}