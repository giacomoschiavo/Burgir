package com.example.burgir

import HomeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.burgir.ui.theme.BurgirTheme


class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      BurgirTheme {
        HomeScreen()
      }
    }
  }
}



@Preview
@Composable
fun HomeScreenPreview() {
  HomeScreen()
}