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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.burgir.data.BurgirViewModel
import com.example.burgir.data.Product

/*
SecondaryScaffold definisce la struttura per i tutti i route che non sono tra i 4 principali
Ha una top app bar predefinita CustomTopBar e non hanno una bottom bar per la navigazione
E' possibile scegliere se mostrare l'icona del carrello o dei favoriti (quest'ultimo non viene mostrato
se non viene passato alcun valore per <product>)
 */


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

  // animazione per la top app bar
  val decayAnimationSpec = rememberSplineBasedDecay<Float>()
  val scrollBehavior =
    remember { TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec) }

  // ottieni i prodotti nel carrello
  burgirViewModel.getProductsinCart()
  val productsInCart by burgirViewModel.products.observeAsState()

  Scaffold(
    modifier = Modifier
      .nestedScroll(scrollBehavior.nestedScrollConnection),
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
    content = { innerPadding ->
      content(
        PaddingValues(
          top = innerPadding.calculateTopPadding(),
          bottom = innerPadding.calculateBottomPadding(),
          start = 15.dp,
          end = 15.dp
        )
      )
    }
  )
}