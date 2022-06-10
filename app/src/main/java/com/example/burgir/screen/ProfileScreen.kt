package com.example.burgir.screen

import PriceLabel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.burgir.R
import com.example.burgir.components.PrimaryScaffold
import com.example.burgir.data.BurgirViewModel
import com.example.burgir.ui.theme.AppTypography
import com.example.burgir.ui.theme.Shapes
import com.example.compose.BurgirTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, burgirViewModel: BurgirViewModel) {

  val carts by burgirViewModel.carts.observeAsState(emptyList())

  PrimaryScaffold(
    navController = navController,
    burgirViewModel = burgirViewModel,
  ) { innerPadding ->
    LazyColumn(
      modifier = Modifier
        .fillMaxWidth()
        .padding(innerPadding)
    ) {
      item {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Image(
            painter = painterResource(id = R.drawable.burgir_icon),
            contentDescription = "Profile picture",
            modifier = Modifier
              .size(100.dp)
              .clip(shape = Shapes.large)
          )
          Text(
            text = "Hasbulla",
            style = AppTypography.displaySmall,
            modifier = Modifier.paddingFromBaseline(top = 30.dp),
            textAlign = TextAlign.Center
          )
        }
      }
      item {
        Text(
          text = "Your Latest Orders",
          modifier = Modifier.padding(10.dp),
          style = AppTypography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
          color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
        )
      }
      items(carts) { cart ->
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.padding(vertical = 10.dp)
        ) {
          Card() {
            Text(
              text = cart.id.toString(),
              style = AppTypography.titleLarge.copy(fontWeight = FontWeight.Bold),
              modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)
            )
          }
          PriceLabel(cart.price, style = AppTypography.titleLarge)
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