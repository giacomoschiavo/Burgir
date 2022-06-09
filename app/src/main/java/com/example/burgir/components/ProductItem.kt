import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.burgir.R
import com.example.burgir.data.Product
import com.example.burgir.navigation.RouteConfig
import com.example.burgir.ui.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItem(product: Product, navController: NavController, modifier: Modifier = Modifier) {

  val navigateToProduct: (Int) -> Unit = {
    navController.navigate("${RouteConfig.PRODUCT_SCREEN_ROUTE}/$it") { launchSingleTop }
  }

  Box(
    contentAlignment = Alignment.Center,
    modifier = modifier.padding(12.dp)
  ) {
    ElevatedCard(
      onClick = { navigateToProduct(product.id) },
    ) {
      Column(
        modifier = Modifier
          .padding(15.dp)
          .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
      ) {
        if (product.discount > 0) Text(
          text = "${product.discount}% OFF",
          style = AppTypography.bodySmall.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.secondary,
        )
        Image(
          painter = painterResource(product.imageUrl),
          contentDescription = null,
          modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .scale(1.1f)
        )

        Column(
          verticalArrangement = Arrangement.SpaceBetween,
          horizontalAlignment = Alignment.CenterHorizontally,
        ) {
          Text(
            text = product.productName,
            style = AppTypography.titleMedium,
            textAlign = TextAlign.Center
          )
          PriceLabel(
            price = product.productPrice,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
            style = AppTypography.titleSmall,
            modifier = Modifier.padding(vertical = 5.dp)
          )
        }
      }
    }
  }
}

@Preview(showBackground = true, widthDp = 200, heightDp = 250)
@Composable
fun ProductItemPreview() {
  ProductItem(
    product = Product(
      0,
      "Hamburger",
      2.50,
      20,
      category = 0,
      imageUrl = R.drawable.b_bigmac
    ), rememberNavController()
  )
}