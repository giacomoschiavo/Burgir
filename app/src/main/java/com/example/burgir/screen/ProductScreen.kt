package com.example.burgir.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.burgir.ui.theme.BurgirTheme

@Composable
fun ProductScreen(productId: Int) {
  BurgirTheme() {
    Column {
      Text(text = "Product Screen")
      Text(text = "$productId")
    }

  }
}