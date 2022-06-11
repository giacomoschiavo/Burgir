package com.example.burgir

import NavigationController
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import com.example.burgir.data.BurgirApplication
import com.example.burgir.data.BurgirViewModel
import com.example.burgir.data.BurgirViewModelFactory
import com.example.burgir.ui.theme.BurgirTheme
import com.google.android.material.color.DynamicColors


class MainActivity : ComponentActivity() {

  private val burgirViewModel: BurgirViewModel by viewModels {
    BurgirViewModelFactory((application as BurgirApplication).repository)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    DynamicColors.applyIfAvailable(this)
    setContent {
      BurgirTheme() {
        Surface() {
          NavigationController(burgirViewModel)
        }
      }
    }
  }
}
