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
import com.example.burgir.navigation.AppState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimaryScaffold(
  appState: AppState,
  modifier: Modifier = Modifier,
  content: @Composable (PaddingValues) -> Unit
) {

  val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }

  Scaffold(
    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    topBar = { LogoWithCartTopAppBar(appState.navController, scrollBehavior) },
    bottomBar = {
      when (appState.getCurrentRoute().substringBefore("/")) {
        AppState.PRODUCT_SCREEN_ROUTE, AppState.SPLASH_SCREEN_ROUTE, AppState.CATEGORY_SCREEN_ROUTE, AppState.CART_SCREEN_ROUTE -> {}
        else -> MainNavigationBar(appState.navController)
      }
    },
    content = { content(it) }
  )

}
