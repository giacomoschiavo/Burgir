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
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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
              product = product,
              modifier = Modifier.padding(10.dp),
              onAdd = { burgirViewModel.addToCart(product.id) },
              onRemove = { burgirViewModel.removeFromCart(product.id) },
              removeAll = { burgirViewModel.removeAllFromCartByProductId(product.id) }
            )
            Divider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))
          }
        }

        val originalPrice = productsOnCart.sumOf { it.productPrice * it.cartQuantity }
        val discount =
          productsOnCart.sumOf { (it.productPrice / 100 * it.discount) * it.cartQuantity }
        PaymentSummary(
          checkout = { burgirViewModel.checkout(originalPrice - discount) },
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
fun RowCartItem(
  product: Product,
  onAdd: (Int) -> Unit,
  onRemove: (Int) -> Unit,
  removeAll: () -> Unit,
  modifier: Modifier = Modifier
) {
  Row(
    horizontalArrangement = Arrangement.SpaceEvenly,
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
  ) {
    Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
      Image(
        painter = painterResource(id = product.imageUrl),
        contentDescription = "product image",
        modifier = Modifier
          .size(100.dp)
          .padding(10.dp)
      )
      Column(verticalArrangement = Arrangement.SpaceBetween) {
        Text(
          text = product.productName,
          style = AppTypography.titleSmall.copy(fontWeight = FontWeight.Bold)
        )
        QuantitySelector(
          onAdd = onAdd,
          onRemove = onRemove,
          modifier = Modifier.padding(top = 10.dp),
          initialQuantity = product.cartQuantity
        )
      }
    }
    Column(
      modifier = Modifier
        .weight(0.4f),
      horizontalAlignment = Alignment.End
    ) {
      IconButton(onClick = removeAll) {
        Icon(
          imageVector = Icons.Outlined.Delete,
          contentDescription = "delete icon",
          modifier = Modifier.padding(10.dp)
        )
      }
      PriceLabel(
        price = product.productPrice - (product.productPrice / 100 * product.discount),
        style = AppTypography.titleLarge
      )
      if (product.discount > 0) {
        PriceLabel(
          price = product.productPrice,
          style = AppTypography.bodySmall,
          color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
        )
      }
    }
  }

}

@Composable
fun PaymentSummary(
  checkout: () -> Unit,
  originalPrice: Double,
  discount: Double,
  modifier: Modifier = Modifier
) {

  Column(modifier = modifier) {
    Column(
      verticalArrangement = Arrangement.SpaceAround,
      modifier = Modifier
        .weight(1f)
        .padding(vertical = 5.dp)
    ) {
      PaymentSummaryItem("Items", originalPrice)
      PaymentSummaryItem("Discount", discount)
      PaymentSummaryItem("Cost", originalPrice - discount)
    }
    Button(onClick = checkout, modifier = Modifier
      .fillMaxWidth()
      .weight(0.3f)) {
      Text(text = "Payment & delivery")
      Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "arrow")
    }
  }
}


@Composable
fun PaymentSummaryItem(key: String, value: Double) {
  Row() {
    Text(text = key, modifier = Modifier.weight(1f), style = AppTypography.titleMedium)
    PriceLabel(price = value, style = AppTypography.titleMedium)
  }
}


@Preview(showBackground = true, heightDp = 120)
@Composable
fun RowCartItemPreview() {
  RowCartItem(Product(
    id = 0,
    productName = "Big Mac",
    imageUrl = R.drawable.b_bigmac,
    category = 1,
    discount = 10,
    isFavorite = true,
  ), onAdd = {}, onRemove = {}, removeAll = {})
}


@Preview(showBackground = true, heightDp = 250)
@Composable
fun PaymentSummaryPreview() {
  PaymentSummary(discount = 12.0, originalPrice = 12.34, checkout = {})
}