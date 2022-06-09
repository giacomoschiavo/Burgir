package com.example.burgir.components

import CustomTopBar
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavController
import com.example.burgir.data.BurgirViewModel
import com.example.burgir.data.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondaryScaffold(
  navController: NavController,
  burgirViewModel: BurgirViewModel,
  content: @Composable (PaddingValues) -> Unit,
  showFavoriteIcon: Boolean = false,
  showCartIcon: Boolean = false,
  title: String = "",
  product: Product? = null,
  onFavoriteClick: (Product) -> Unit = {},
) {
  val decayAnimationSpec = rememberSplineBasedDecay<Float>()
  val scrollBehavior =
    remember { TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec) }

  burgirViewModel.getProductsinCart()
  val productsInCart by burgirViewModel.products.observeAsState()

  Scaffold(
    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    topBar = {
      CustomTopBar(
        navController = navController,
        scrollBehavior = scrollBehavior,
        title = title,
        product = product,
        showCartIcon = showCartIcon,
        showFavoriteIcon = showFavoriteIcon,
        onFavoriteClick = onFavoriteClick,
        showBadge = { productsInCart?.isNotEmpty() ?: false }
      )
    },
    content = { content(it) }
  )
}