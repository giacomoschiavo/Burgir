import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.burgir.data.Product

@Composable
fun CategoryScreen(
  categoryId: Int,
  navigateToProduct: (Int) -> Unit,
  modifier: Modifier = Modifier,
  products: List<Product>
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
  CategoryScreen(
    categoryId = 0,
    navigateToProduct = {},
    products = products.filter { product -> product.category == 0 })
}