import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.burgir.components.SecondaryScaffold
import com.example.burgir.data.BurgirViewModel

@Composable
fun CategoryScreen(
  navController: NavController,
  categoryId: Int,
  modifier: Modifier = Modifier,
  burgirViewModel: BurgirViewModel
) {

  if (categoryId == -1) {
    Text(text = "Category not found!")
    return
  }


  burgirViewModel.getProductsByCategory(categoryId)
  val productsByCategory by burgirViewModel.products.observeAsState(emptyList())
  val categories by burgirViewModel.categories.observeAsState(emptyList())
  val category = categories.find { category -> categoryId == category.id }

  SecondaryScaffold(
    navController = navController,
    burgirViewModel = burgirViewModel,
    showCartIcon = true,
    title = category?.categoryName ?: "",
    content = { innerPadding ->
      ProductsGrid(
        navController = navController,
        products = productsByCategory,
        modifier = modifier.padding(innerPadding),
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