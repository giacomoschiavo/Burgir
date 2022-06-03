import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.burgir.R
import com.example.burgir.data.Product
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
      modifier = Modifier.heightIn(200.dp)
    ) {
      Column(
        modifier = Modifier.padding(15.dp), horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        if (product.discount > 0) Text(
          text = "${product.discount}% OFF",
          style = AppTypography.bodySmall.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.secondary,
          modifier = Modifier.offset(y = 20.dp)
        )
        Image(
          painter = painterResource(product.imageUrl),
          contentDescription = null,
          modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Text(
            text = product.productName,
            style = AppTypography.titleMedium,
            textAlign = TextAlign.Center
          )
          PriceLabel(
            price = product.productPrice,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
            style = AppTypography.titleSmall
          )
        }
      }
    }
  }
}

@Preview(showBackground = true, widthDp = 200)
@Composable
fun ProductItemPreview() {
  BurgirTheme {
    ProductItem(
      product = Product(
        0,
        "Hamburger",
        2.50,
        20,
        category = 0,
        imageUrl = R.drawable.b_bigmac
      ), navigateToProduct = {})
  }
}