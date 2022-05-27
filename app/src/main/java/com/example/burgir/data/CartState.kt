package com.example.burgir.data

import Product
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class CartState(
  val products: List<Product> = listOf()

)
