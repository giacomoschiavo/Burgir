import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.burgir.R
import com.example.burgir.data.Product
import com.example.burgir.ui.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDescription(
  product: Product?,
  onAddToCart: (productId: Int, quantity: Int) -> Unit,
  modifier: Modifier = Modifier
) {
  var quantity by rememberSaveable { mutableStateOf(1) }
  var clicked by rememberSaveable() { mutableStateOf(false) }
  Surface(
    modifier = modifier
  ) {
    if (product == null) {
      Text("No products found :(")
    } else {

      val isDiscounted = product.discount > 0

      Column {
        if (isDiscounted) {
          Text(
            "${product.discount}% OFF",
            style = AppTypography.labelLarge,
            color = MaterialTheme.colorScheme.primary
          )
        }
        Text(
          text = product.productName,
          modifier = Modifier.padding(vertical = 10.dp),
          style = AppTypography.displaySmall
        )
        if (isDiscounted) {
          PriceLabel(
            price = product.productPrice - (product.productPrice / 100 * product.discount),
            style = AppTypography.headlineMedium,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
          )
        }
        PriceLabel(
          price = product.productPrice,
          style = if (isDiscounted) AppTypography.titleMedium else AppTypography.headlineMedium,
          color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
        )
        Text(
          text = product.description,
          style = AppTypography.bodyMedium,
          modifier = Modifier
            .padding(vertical = 20.dp)
        )
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.padding(vertical = 10.dp)
        ) {
          QuantitySelector(
            onAdd = { quantity = it; clicked = false },
            onRemove = { quantity = it; clicked = false },
            modifier = Modifier
              .padding(horizontal = 10.dp)
              .weight(0.8f)
          )
          Button(
            onClick = { onAddToCart(product.id, quantity); clicked = true },
            modifier = Modifier
              .weight(1f)
              .heightIn(60.dp)
          ) {
            AnimatedVisibility(visible = !clicked) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "Cart")
                Text(
                  text = "Add to cart",
                  style = AppTypography.labelLarge,
                  modifier = Modifier.padding(10.dp)
                )
              }
            }
            AnimatedVisibility(visible = clicked) {
              Icon(imageVector = Icons.Filled.Check, contentDescription = "Cart")
            }
          }
        }
      }

    }
  }
}


@Preview(showBackground = true)
@Composable
fun ProductDescriptionDiscountedPreview() {
  ProductDescription(
    product = Product(
      0,
      "Hamburger",
      2.50,
      20,
      category = 0,
      imageUrl = R.drawable.b_bigmac
    ),
    onAddToCart = { _, _ -> }
  )
}

@Preview(showBackground = true)
@Composable
fun ProductDescriptionPreview() {
  ProductDescription(
    product = Product(
      0,
      "Hamburger",
      2.50,
      0,
      category = 0,
      imageUrl = R.drawable.b_bigmac
    ),
    onAddToCart = { _, _ -> }
  )
}