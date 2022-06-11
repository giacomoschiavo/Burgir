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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.burgir.R
import com.example.burgir.components.PrimaryScaffold
import com.example.burgir.data.BurgirViewModel
import com.example.burgir.ui.theme.AppTypography
import com.example.burgir.ui.theme.Shapes

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
        .padding(innerPadding),
      horizontalAlignment = Alignment.CenterHorizontally,
      contentPadding = PaddingValues(horizontal = 15.dp),
    ) {
      item {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.padding(horizontal = 20.dp)
        ) {
          Image(
            painter = painterResource(id = R.drawable.burgir_icon),
            contentDescription = "Profile picture",
            modifier = Modifier
              .size(100.dp)
              .clip(shape = Shapes.large)
          )
          Text(
            text = stringResource(id = R.string.profile_name),
            style = AppTypography.displaySmall,
            modifier = Modifier
              .paddingFromBaseline(top = 30.dp)
              .weight(1f)
              .padding(horizontal = 15.dp),
            color = MaterialTheme.colorScheme.onBackground
          )
        }
      }
      item {
        Text(
          text = "Your Latest Orders",
          modifier = Modifier.padding(vertical = 15.dp),
          style = AppTypography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
          color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
        )
      }
      item {
        Row(modifier = Modifier
          .padding(vertical = 10.dp)
          .fillMaxWidth()) {
          Text(
            text = "Cart Id",
            style = AppTypography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
          )
          Text(
            text = "Price",
            style = AppTypography.titleMedium,
            modifier = Modifier.padding(horizontal = 20.dp),
            color = MaterialTheme.colorScheme.onBackground
          )
        }
      }
      items(carts, key = { it.id }) { cart ->
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
        ) {
          Card {
            Text(
              text = cart.id.toString(),
              style = AppTypography.titleLarge.copy(fontWeight = FontWeight.Bold),
              modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)
            )
          }
          PriceLabel(
            cart.price,
            style = AppTypography.titleLarge,
            modifier = Modifier.padding(horizontal = 15.dp)
          )
        }
      }
    }
  }
}