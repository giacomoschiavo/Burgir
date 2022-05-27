package com.example.burgir.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Image(
        modifier = Modifier
          .padding(20.dp)
          .size(220.dp),
        painter = painterResource(id = product!!.imageUrl),
        contentDescription = "image of the product"
      )
      ElevatedCard(
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primary)
      ) {
        Text(
          text = product.name,
          style = MaterialTheme.typography.displayMedium,
          modifier = Modifier.padding(8.dp)
        )
      }

      Text(text = product.description)
    }
  }

}

@Preview(showBackground = true)
@Composable
fun ProductScreenPreview() {
  ProductScreen(rememberNavController(), 0)
}