import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.burgir.data.BurgirViewModel

@Composable
fun CategoryScreen(
  categoryId: Int,
  navigateToProduct: (Int) -> Unit,
  modifier: Modifier = Modifier
) {


  ProductsGrid(
    products = products.filter { product -> product.category == categoryId },
    navigateToProduct = navigateToProduct,
    modifier = modifier
  )
}

@Preview(showBackground = true)
@Composable
fun CategoryScreenPreview() {
  CategoryScreen(categoryId = 0, navigateToProduct = {})
}