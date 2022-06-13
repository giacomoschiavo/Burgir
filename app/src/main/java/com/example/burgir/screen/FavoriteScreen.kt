import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.burgir.components.PrimaryScaffold
import com.example.burgir.data.BurgirViewModel
import com.example.burgir.ui.theme.AppTypography

/*
Schermata FavoriteScreen
Contiene tutti i prodotti preferiti dall'utente.
Al click di ogni prodotto verra' aperta la pagina di descrizione di tale prodotto.
La lista di prodotti sono estratti dal ViewModel
 */

@Composable
fun FavoriteScreen(
  navController: NavController,
  burgirViewModel: BurgirViewModel,
) {


  // ottieni la lista dei prodotti dal ViewModel
  burgirViewModel.getProductsByFavorite()

  // osserva per qualsiasi cambiamento
  val favoriteProducts by burgirViewModel.products.observeAsState(emptyList())

  PrimaryScaffold(
    navController = navController,
    burgirViewModel = burgirViewModel,
  ) { innerPadding ->
    // crea una griglia con i prodotti passati in <products>
    ProductsGrid(
      navController = navController,
      products = favoriteProducts,
      header = {    // parametro opzionale, specifica un header sopra la griglia
        Text(
          text = "Your Favorites♥",
          textAlign = TextAlign.Center,
          style = AppTypography.headlineMedium.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
          modifier = Modifier.padding(top = 15.dp, bottom = 15.dp)
        )
      },
      modifier = Modifier.padding(innerPadding)
    )
  }
}