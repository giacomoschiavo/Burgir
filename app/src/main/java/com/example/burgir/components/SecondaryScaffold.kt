package com.example.burgir.components

import CustomTopBar
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.burgir.data.Product
import com.example.burgir.navigation.AppState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondaryScaffold(
  appState: AppState,
  content: @Composable (PaddingValues) -> Unit,
  showFavoriteIcon: Boolean = false,
  showCartIcon: Boolean = false,
  title: String = "",
  productId: Int = -1,
  onFavoriteClick: (Product) -> Unit = {}
) {

  if (productId == -1) return

  val decayAnimationSpec = rememberSplineBasedDecay<Float>()
  val scrollBehavior =
    remember { TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec) }

  Scaffold(
    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    topBar = {
      CustomTopBar(
        navController = appState.navController,
        scrollBehavior = scrollBehavior,
        title = title,
        products = appState.products,
        productId = productId,
        showCartIcon = showCartIcon,
        showFavoriteIcon = showFavoriteIcon,
        onFavoriteClick = onFavoriteClick
      )
    },
    content = { content(it) }
  )
}