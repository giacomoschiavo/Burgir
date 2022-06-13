import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.burgir.R
import com.example.burgir.components.SecondaryScaffold
import com.example.burgir.data.BurgirViewModel

/*
Schermata Category
Visualizza tutti i prodotti che appartengono alla stessa categoria selezionata
Vengono mostrati in ProductsGrid
 */

@Composable
fun CategoryScreen(
  navController: NavController,
  categoryId: Int,
  modifier: Modifier = Modifier,
  burgirViewModel: BurgirViewModel
) {

  if (categoryId == -1) {
    Text(text = stringResource(R.string.err_category_not_found))
    return
  }

  // ottieni tutti i prodotti di tale categoria
  burgirViewModel.getProductsByCategory(categoryId)
  // osserva i prodotti di categoria e le categorie
  val productsByCategory by burgirViewModel.products.observeAsState(emptyList())
  val categories by burgirViewModel.categories.observeAsState(emptyList())
  // ottieni la categoria con id corrispondente
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