package com.example.burgir.components

import LogoWithCartTopAppBar
import MainNavigationBar
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

/*
PrimaryScaffold definisce la struttura per i 4 routes principali: Home, Search, Favorite e Profile
Ha una top app bar predefinita LogoWithCartTopAppBar che permette di gestire il carrello
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimaryScaffold(
  navController: NavController,
  burgirViewModel: BurgirViewModel,
  modifier: Modifier = Modifier,
  content: @Composable (PaddingValues) -> Unit
) {

  // serve alla top app bar per "fissarsi" in alto
  val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }

  // ottieni ed osserva in prodotti nel carrello
  burgirViewModel.getProductsinCart()
  val productsInCart by burgirViewModel.products.observeAsState()

  Scaffold(
    modifier = modifier
      .nestedScroll(scrollBehavior.nestedScrollConnection),
    topBar = {
      LogoWithCartTopAppBar(
        navController,
        scrollBehavior,
        { productsInCart?.isNotEmpty() ?: false })
    },
    bottomBar = { MainNavigationBar(navController) },
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
