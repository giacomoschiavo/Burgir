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
import com.example.burgir.data.Product
import com.example.burgir.ui.theme.AppTypography
import com.example.burgir.ui.theme.Shapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDescription(
  product: Product?,
  onAddToCart: (productId: Int, quantity: Int) -> Unit,
  modifier: Modifier = Modifier
) {
  Surface(
    shape = Shapes.large,
    modifier = modifier
  ) {

    var quantity by rememberSaveable { mutableStateOf(1) }
    var clicked by rememberSaveable() { mutableStateOf(false) }

    if (product == null) {
      Text("No products found :(")
    } else {
      Column {
        if (product.discount != 0) {
          ElevatedSuggestionChip(
            onClick = {},
            enabled = false,
            label = { Text("${product.discount}% OFF", style = AppTypography.labelMedium) },
          )
        }
        Text(
          text = product.productName,
          modifier = Modifier.padding(vertical = 10.dp),
          style = AppTypography.displaySmall
        )
        PriceLabel(
          price = product.productPrice,
          style = AppTypography.titleLarge,
          color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
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
fun ProductDescriptionPreview() {
//  ProductDescription(products[0])
}