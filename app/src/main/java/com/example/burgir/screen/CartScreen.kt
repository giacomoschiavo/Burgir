package com.example.burgir.screen

import PriceLabel
import Product
import QuantitySelector
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.burgir.R
import com.example.burgir.ui.theme.AppTypography
import com.example.burgir.ui.theme.BurgirTheme

val cartList = listOf(
  Product(
    id = 0,
    name = "Insalata strana",
    imageUrl = R.drawable.burger,
    categoryId = 1,
    sales = 10,
    isFavorite = true,
    quantity = 2,
  ),
  Product(
    id = 2,
    name = "Dolce buonissimo",
    imageUrl = R.drawable.burger,
    categoryId = 2,
    quantity = 1,
  ),
  Product(
    id = 6,
    quantity = 1,
    name = "Burger",
    imageUrl = R.drawable.burger
  ),
  Product(
    id = 7,
    name = "Burger",
    quantity = 1,
    imageUrl = R.drawable.burger
  ),
  Product(
    id = 8,
    name = "Burger",
    quantity = 1,
    imageUrl = R.drawable.burger
  ),
  Product(
    id = 9,
    name = "Burger",
    quantity = 1,
    imageUrl = R.drawable.burger
  ),
)

@Composable
fun CartScreen(navController: NavController) {
  Column() {
    LazyColumn(
      modifier = Modifier.weight(0.8f),
      contentPadding = PaddingValues(vertical = 10.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      items(cartList) { product ->
        RowCartItem(product, modifier = Modifier.padding(10.dp))
      }
    }
    PaymentSummary(
      modifier = Modifier
        .weight(0.2f)
        .padding(15.dp)
        .heightIn(180.dp)
    )

  }
}


@Composable
fun RowCartItem(product: Product, modifier: Modifier = Modifier) {
  Row(
    horizontalArrangement = Arrangement.SpaceEvenly,
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
  ) {
    Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
      Image(
        painter = painterResource(id = product.imageUrl),
        contentDescription = "image",
        modifier = Modifier
          .size(100.dp)
          .padding(10.dp)
      )
      Spacer(modifier = Modifier.size(10.dp))
      Column(verticalArrangement = Arrangement.SpaceBetween) {
        Text(
          text = product.name,
          style = AppTypography.titleSmall.copy(fontWeight = FontWeight.Bold)
        )
        QuantitySelector(modifier = Modifier.padding(top = 10.dp))
      }
    }
    Column(
      modifier = Modifier
        .weight(0.3f),
      verticalArrangement = Arrangement.SpaceAround,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      IconButton(onClick = { /*TODO*/ }) {
        Icon(imageVector = Icons.Outlined.Delete, contentDescription = "delete icon")
      }
      PriceLabel(price = product.price)
    }
  }

}

@Composable
fun PaymentSummary(modifier: Modifier = Modifier) {
  Column(modifier = modifier) {
    Column(verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.weight(1f)) {
      PaymentSummaryItem("Items", "$20.60")
      PaymentSummaryItem("Discount", "-0.60$")
      PaymentSummaryItem("Cost", "$19.90")
    }
    Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
      Text(text = "Payment & delivery")
      Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "arrow")
    }
  }
}

@Composable
fun PaymentSummaryItem(key: String, value: String) {
  Row() {
    Text(text = key, modifier = Modifier.weight(1f))
    Text(text = value)
  }
}


@Preview(showBackground = true, heightDp = 120)
@Composable
fun RowCartItemPreview() {
  RowCartItem(cartList[0])
}


@Preview(showBackground = true, heightDp = 150)
@Composable
fun PaymentSummaryPreview() {
  PaymentSummary()
}


@Preview(showBackground = true, heightDp = 700, widthDp = 400)
@Composable
fun CartScreenPreview() {
  CartScreen(rememberNavController())
}


