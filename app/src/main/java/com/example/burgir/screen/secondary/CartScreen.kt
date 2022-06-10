package com.example.burgir.screen.secondary

import PriceLabel
import QuantitySelector
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.burgir.R
import com.example.burgir.components.SecondaryScaffold
import com.example.burgir.data.BurgirViewModel
import com.example.burgir.data.Product
import com.example.burgir.ui.theme.AppTypography


@Composable
fun CartScreen(
  navController: NavController,
  burgirViewModel: BurgirViewModel
) {

  burgirViewModel.getProductsinCart()
  val productsOnCart by burgirViewModel.products.observeAsState(emptyList())

  SecondaryScaffold(
    navController = navController,
    burgirViewModel = burgirViewModel,
    title = "Your Cart",
    content = { innerPadding ->
      Column(modifier = Modifier.padding(innerPadding)) {
        LazyColumn(
          modifier = Modifier.weight(0.8f),
          contentPadding = PaddingValues(vertical = 10.dp),
          horizontalAlignment = Alignment.CenterHorizontally,
        ) {
          items(productsOnCart, key = { product -> product.id }) { product ->
            RowCartItem(
              product,
              modifier = Modifier.padding(10.dp),
              burgirViewModel = burgirViewModel
            )
          }
        }

        val originalPrice = productsOnCart.sumOf { it.productPrice * it.cartQuantity }
        val discount =
          productsOnCart.sumOf { (it.productPrice / 100 * it.discount) * it.cartQuantity }
        PaymentSummary(
          burgirViewModel = burgirViewModel,
          originalPrice = originalPrice,
          discount = discount,
          modifier = Modifier
            .weight(0.3f)
            .padding(15.dp)
        )
    }
  })

}


@Composable
fun RowCartItem(product: Product, burgirViewModel: BurgirViewModel, modifier: Modifier = Modifier) {
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
          text = product.productName,
          style = AppTypography.titleSmall.copy(fontWeight = FontWeight.Bold)
        )
        QuantitySelector(
          onAdd = { burgirViewModel.addToCart(product.id) },
          onRemove = { burgirViewModel.removeFromCart(product.id) },
          modifier = Modifier.padding(top = 10.dp),
          initialQuantity = product.cartQuantity
        )
      }
    }
    Column(
      modifier = Modifier
        .weight(0.3f),
      verticalArrangement = Arrangement.Top,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      IconButton(onClick = { burgirViewModel.removeAllFromCartByProductId(product.id) }) {
        Icon(imageVector = Icons.Outlined.Delete, contentDescription = "delete icon")
      }
      PriceLabel(price = product.productPrice)
    }
  }

}

@Composable
fun PaymentSummary(
  burgirViewModel: BurgirViewModel,
  originalPrice: Double,
  discount: Double,
  modifier: Modifier = Modifier
) {

  Column(modifier = modifier) {
    Column(
      verticalArrangement = Arrangement.SpaceEvenly,
      modifier = Modifier
        .weight(1f)
        .padding(vertical = 5.dp)
    ) {
      PaymentSummaryItem("Items", originalPrice)
      PaymentSummaryItem("Discount", discount)
      PaymentSummaryItem("Cost", originalPrice - discount)
    }
    Button(onClick = { burgirViewModel.checkout() }, modifier = Modifier.fillMaxWidth()) {
      Text(text = "Payment & delivery")
      Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "arrow")
    }
  }
}

val cartList = listOf(
  Product(
    id = 0,
    productName = "Insalata strana",
    imageUrl = R.drawable.burger,
    category = 1,
    discount = 10,
    isFavorite = true,
  ),
  Product(
    id = 2,
    productName = "Dolce buonissimo",
    imageUrl = R.drawable.burger,
    category = 2,
    cartQuantity = 1,
  ),
  Product(
    id = 6,
    cartQuantity = 1,
    productName = "Burger",
    imageUrl = R.drawable.burger,
    category = 0,
  ),
  Product(
    id = 7,
    productName = "Burger",
    cartQuantity = 1,
    imageUrl = R.drawable.burger,
    category = 0,
  ),
  Product(
    id = 8,
    productName = "Burger",
    cartQuantity = 1,
    imageUrl = R.drawable.burger,
    category = 0,
  ),
  Product(
    id = 9,
    productName = "Burger",
    cartQuantity = 1,
    imageUrl = R.drawable.burger,
    category = 0,
  ),
)

@Composable
fun PaymentSummaryItem(key: String, value: Double) {
  Row() {
    Text(text = key, modifier = Modifier.weight(1f))
    PriceLabel(price = value)
  }
}


@Preview(showBackground = true, heightDp = 120)
@Composable
fun RowCartItemPreview() {
  RowCartItem(cartList[0], viewModel())
}


@Preview(showBackground = true, heightDp = 150)
@Composable
fun PaymentSummaryPreview() {
  PaymentSummary(discount = 12.0, originalPrice = 12.34, burgirViewModel = viewModel())
}


@Preview(showBackground = true, heightDp = 700, widthDp = 400)
@Composable
fun CartScreenPreview() {
  CartScreen(rememberNavController(), viewModel())
}

