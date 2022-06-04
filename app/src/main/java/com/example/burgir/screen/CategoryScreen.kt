import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.burgir.components.SecondaryScaffold
import com.example.burgir.data.Product
import com.example.burgir.navigation.AppState

@Composable
fun CategoryScreen(
  categoryId: Int,
  navigateToProduct: (Int) -> Unit,
  modifier: Modifier = Modifier,
  products: List<Product>,
  appState: AppState
) {
  SecondaryScaffold(
    appState = appState,
    showCartIcon = true,
    title = appState.getCategoryNameById(categoryId),
    content = { innerPadding ->
      ProductsGrid(
        products = products.filter { product -> product.category == categoryId },
        navigateToProduct = navigateToProduct,
        modifier = modifier.padding(innerPadding),
        appState = appState
      )
    })
}

@Preview(showBackground = true)
@Composable
fun CategoryScreenPreview() {
//  CategoryScreen(
//    categoryId = 0,
//    navigateToProduct = {},
//    products = products.filter { product -> product.category == 0 },
//    appState = appState
//  )
}