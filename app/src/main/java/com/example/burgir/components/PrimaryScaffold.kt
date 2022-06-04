package com.example.burgir.components

import LogoWithCartTopAppBar
import MainNavigationBar
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.burgir.MainActivity
import com.example.burgir.navigation.AppState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimaryScaffold(appState: AppState, content: @Composable (PaddingValues) -> Unit) {

  val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }

  Scaffold(
    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    topBar = { LogoWithCartTopAppBar(appState.navController, scrollBehavior) },
    bottomBar = {
      when (appState.getCurrentRoute().substringBefore("/")) {
        MainActivity.PRODUCT_SCREEN_ROUTE, MainActivity.SPLASH_SCREEN_ROUTE, MainActivity.CATEGORY_SCREEN_ROUTE, MainActivity.CART_SCREEN_ROUTE -> {}
        else -> MainNavigationBar(appState.navController)
      }
    },
    content = { content(it) }
  )

}
