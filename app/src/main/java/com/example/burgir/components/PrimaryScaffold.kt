package com.example.burgir.components

import LogoWithCartTopAppBar
import MainNavigationBar
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.burgir.navigation.RouteConfig


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimaryScaffold(
  navController: NavController,
  modifier: Modifier = Modifier,
  content: @Composable (PaddingValues) -> Unit
) {

  val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
  val backStackEntry by navController.currentBackStackEntryAsState()
  val currentRoute = backStackEntry?.destination?.route ?: ""

  Scaffold(
    modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    topBar = { LogoWithCartTopAppBar(navController, scrollBehavior) },
    bottomBar = {
      when (currentRoute.substringBefore("/")) {
        RouteConfig.PRODUCT_SCREEN_ROUTE, RouteConfig.SPLASH_SCREEN_ROUTE, RouteConfig.CATEGORY_SCREEN_ROUTE, RouteConfig.CART_SCREEN_ROUTE -> {}
        else -> MainNavigationBar(navController)
      }
    },
    content = { content(it) }
  )

}
