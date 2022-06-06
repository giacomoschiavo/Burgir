package com.example.burgir.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.burgir.data.Product

class DataViewModel : ViewModel() {

  private val _products = MutableLiveData<List<Product>>(emptyList())
  val products: LiveData<List<Product>> = _products

  fun fillProducts(newProducts: List<Product>) {
    _products.value = newProducts
  }

}