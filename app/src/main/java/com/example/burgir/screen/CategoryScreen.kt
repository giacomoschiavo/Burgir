import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun CategoryScreen(
  categoryId: Int,
  navigateToProduct: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  ProductsGrid(
    title = { Text(text = categories[categoryId].name) },
    products = products.filter { product -> product.categoryId == categoryId },
    navigateToProduct = navigateToProduct,
    modifier = modifier
  )
}