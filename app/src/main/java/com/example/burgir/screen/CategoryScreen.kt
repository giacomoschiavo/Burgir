import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.burgir.components.SecondaryScaffold
import com.example.burgir.data.BurgirViewModel
import com.example.burgir.navigation.AppState

@Composable
fun CategoryScreen(
  categoryId: Int,
  navigateToProduct: (Int) -> Unit,
  modifier: Modifier = Modifier,
  appState: AppState,
  burgirViewModel: BurgirViewModel
) {

  burgirViewModel.getProductsByCategory(categoryId)
  val productsByCategory by burgirViewModel.products.observeAsState(emptyList())

  SecondaryScaffold(
    appState = appState,
    showCartIcon = true,
    title = appState.getCategoryNameById(categoryId),
    content = { innerPadding ->
      ProductsGrid(
        products = productsByCategory,
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