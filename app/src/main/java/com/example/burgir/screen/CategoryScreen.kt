import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CategoryScreen(
  categoryId: Int,
  navigateToProduct: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  ProductsGrid(
    products = products.filter { product -> product.categoryId == categoryId },
    navigateToProduct = navigateToProduct,
    modifier = modifier
  )
}

@Preview(showBackground = true)
@Composable
fun CategoryScreenPreview() {
  CategoryScreen(categoryId = 0, navigateToProduct = {})
}