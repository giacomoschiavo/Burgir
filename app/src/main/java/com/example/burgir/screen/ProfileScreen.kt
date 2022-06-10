package com.example.burgir.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.burgir.R
import com.example.burgir.components.PrimaryScaffold
import com.example.burgir.data.BurgirViewModel
import com.example.burgir.ui.theme.AppTypography
import com.example.burgir.ui.theme.Shapes
import com.example.compose.BurgirTheme

@Composable
fun ProfileScreen(navController: NavController, burgirViewModel: BurgirViewModel) {

  burgirViewModel.getProductsByPopularity()
  val carts by burgirViewModel.carts.observeAsState(emptyList())

  PrimaryScaffold(
    navController = navController,
    burgirViewModel = burgirViewModel,
  ) { innerPadding ->
    LazyColumn(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      item {
        Image(
          painter = painterResource(id = R.drawable.profile_pic),
          contentDescription = "Profile picture",
          modifier = Modifier
            .size(200.dp)
            .clip(shape = Shapes.large)
        )
      }
      item {
        Text(
          text = "Shagon",
          style = AppTypography.displaySmall,
          modifier = Modifier.paddingFromBaseline(top = 30.dp)
        )
      }
      item { Text(text = "Your Latest Orders") }
      items(carts) { cart ->
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(text = cart.date)
          Text(text = cart.price.toString())
        }
      }
    }
  }

}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
  BurgirTheme() {
//    ProfileScreen(AppState(rememberNavController(), products, myViewModel))
  }
}