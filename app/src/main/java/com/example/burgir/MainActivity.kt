package com.example.burgir

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.burgir.ui.theme.BurgirTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      BurgirTheme {
        BurgirTopAppBar()
      }
    }
  }
}

@Composable
fun CategorySlider(modifier: Modifier = Modifier) {
  LazyRow(
    modifier = modifier.padding(top = 10.dp),
    contentPadding = PaddingValues(horizontal = 16.dp),
    horizontalArrangement = Arrangement.spacedBy(20.dp),
    ) {
      item() { Category() }
      item() { Category() }
      item() { Category() }
      item() { Category() }
      item() { Category() }
      item() { Category() }
      item() { Category() }
      item() { Category() }
  }
}

@Composable
fun BurgirTopAppBar(modifier: Modifier = Modifier) {
  Scaffold(
    topBar = {
      CenterAlignedTopAppBar(
        title = { Text("Burgir") },
        actions = {
          IconButton(onClick = {  }) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Account Icon"
            )
          }
        },
      )
    }) {
    Column {
      CategorySlider()
      Spacer(modifier = Modifier.size(30.dp))
      ProductContainer()
    }

  }
}


@Composable
fun Product(modifier: Modifier = Modifier) {
  Surface(
    modifier = modifier
      .heightIn(220.dp)
      .clickable { },
    border = BorderStroke(1.dp, MaterialTheme.colors.background)
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Image(
        painter = painterResource(R.drawable.burger),
        contentDescription = null,
        modifier = Modifier
          .size(120.dp)
          .padding(10.dp))
      Text(text = "Burger super buono")
    }
  }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Category(modifier: Modifier = Modifier) {

  var clicked by rememberSaveable { mutableStateOf(false) }

  Card(
    elevation = 5.dp,
    modifier = modifier,
    onClick = { clicked = !clicked },
    backgroundColor = if (clicked) MaterialTheme.colors.primary else MaterialTheme.colors.background
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier.padding(6.dp),
    ) {
      Image(
        painter = painterResource(R.drawable.burger),
        contentDescription = null,
        modifier = Modifier
          .size(90.dp)
          .padding(5.dp)
      )
      Text(
        text = "Burger",
//        style = MaterialTheme.typography.h6,
      )
    }
  }
}

@Composable
fun ProductContainer() {
  LazyVerticalGrid(columns = GridCells.Fixed(2)) {
    item { Product() }
    item { Product() }
    item { Product() }
    item { Product() }
    item { Product() }
    item { Product() }
    item { Product() }
    item { Product() }
    item { Product() }
    item { Product() }
  }
}


@Preview(showBackground = true)
@Composable
fun ProductPreview() {
  BurgirTheme {
    Product()
  }
}


@Preview(showBackground = true, widthDp = 320, heightDp = 600)
@Composable
fun ScreenPreview() {
  BurgirTheme {
    BurgirTopAppBar()
  }
}
