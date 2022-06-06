import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.burgir.data.Product
import com.example.burgir.ui.theme.AppTypography
import com.example.burgir.ui.theme.Shapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDescription(product: Product?, modifier: Modifier = Modifier) {
  Surface(
    shape = Shapes.large,
    modifier = modifier
  ) {

    var quantity = 1

    if (product == null) {
      Text("No products found :(")
    } else {
      Column(modifier = Modifier.padding(15.dp)) {
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
          QuantitySelector({ quantity = it }, modifier = Modifier.padding(horizontal = 10.dp))
          Button(
            onClick = { product.cartQuantity += quantity },
            modifier = Modifier.weight(1f)
          ) {
            Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "Cart")
            Text(
              text = "Add to cart",
              style = AppTypography.labelLarge,
              modifier = Modifier.padding(10.dp)
            )
          }
        }
      }

    }
  }
}


@Preview(showBackground = true)
@Composable
fun ProductDescriptionPreview() {
  ProductDescription(products[0])
}