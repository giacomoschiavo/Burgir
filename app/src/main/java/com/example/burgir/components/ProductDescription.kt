import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.burgir.R
import com.example.burgir.ui.theme.AppTypography
import com.example.burgir.ui.theme.BurgirTheme
import com.example.burgir.ui.theme.Shapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDescription(product: Product, modifier: Modifier = Modifier) {
  Surface(
    shape = Shapes.large,
    modifier = modifier
  ) {
    Column(modifier = Modifier.padding(15.dp)) {
      if (product.sales != 0) {
        AssistChip(
          onClick = {},
          enabled = false,
          label = { Text("${product.sales}% OFF", style = AppTypography.labelMedium) },
        )
      }
      Text(
        text = product.name,
        modifier = Modifier.padding(vertical = 10.dp),
        style = AppTypography.displaySmall
      )
      Text(
        text = product.price,
        modifier = Modifier.padding(vertical = 5.dp),
        style = AppTypography.headlineMedium,
        color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
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
        QuantitySelector(modifier = Modifier.padding(horizontal = 10.dp))
        Button(
          onClick = { },
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


@Preview(showBackground = true)
@Composable
fun ProductDescriptionPreview() {
  BurgirTheme() {
    ProductDescription(products[0])
  }
}