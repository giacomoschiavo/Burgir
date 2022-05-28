import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.burgir.R
import com.example.burgir.screen.ProductScreen
import com.example.burgir.ui.theme.AppTypography
import com.example.burgir.ui.theme.BurgirTheme
import com.example.burgir.ui.theme.Shapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDescription(product: Product, modifier: Modifier = Modifier) {
  Surface(
    shape = Shapes.large,
    color = MaterialTheme.colorScheme.primaryContainer
  ) {
    Column(modifier = Modifier.padding(15.dp)) {
      AssistChip(
        onClick = {},
        label = { Text("${product.sales}% OFF", style = AppTypography.labelMedium) }
      )
      Text(
        text = product.name,
        modifier = Modifier.padding(vertical = 10.dp),
        style = AppTypography.displayMedium
      )
      Text(
        text = product.price,
        modifier = Modifier.padding(vertical = 5.dp),
        style = AppTypography.bodyLarge
      )
      Text(text = product.description, modifier = Modifier.padding(vertical = 10.dp))
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center
        ) {
          IconButton(onClick = { product.quantity-- }) {
            Icon(
              painter = painterResource(id = R.drawable.ic_baseline_remove_24),
              contentDescription = null
            )
          }
          Text(text = product.quantity.toString())
          IconButton(onClick = { product.quantity++ }) {
            Icon(
              imageVector = Icons.Filled.Add,
              contentDescription = null
            )
          }
        }
        Button(
          onClick = { product.quantity++ },
          modifier = Modifier.padding(32.dp)
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


@Preview(showBackground = true)
@Composable
fun ProductDescriptionPreview() {
  BurgirTheme() {
    ProductDescription(products[0])
  }
}