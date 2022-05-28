import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.burgir.ui.theme.AppTypography
import com.example.burgir.ui.theme.BurgirTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItem(product: Product, navigateToProduct: (Int) -> Unit, modifier: Modifier = Modifier) {
  Box(
    contentAlignment = Alignment.Center,
    modifier = modifier.padding(12.dp)
  ) {
    ElevatedCard(
      onClick = { navigateToProduct(product.id) },
    ) {

      Column(
        modifier = modifier.padding(vertical = 30.dp, horizontal = 27.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        if (product.sales > 0) Text(
          text = "${product.sales}% OFF",
          style = AppTypography.labelMedium,
          color = MaterialTheme.colorScheme.secondary,
        )
        Image(
          painter = painterResource(product.imageUrl),
          contentDescription = null,
          modifier = Modifier
            .size(120.dp)
            .padding(10.dp)
        )
        Text(text = product.name, style = AppTypography.bodyMedium)
        Text(
          text = product.price,
          style = AppTypography.labelMedium,
        )
      }
    }
  }
}

@Preview
@Composable
fun ProductItemPreview() {
  BurgirTheme {
    ProductItem(product = products[0], navigateToProduct = {})
  }
}