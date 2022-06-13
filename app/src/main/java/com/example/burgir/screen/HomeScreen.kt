import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.burgir.R
import com.example.burgir.components.PrimaryScaffold
import com.example.burgir.data.BurgirViewModel
import com.example.burgir.ui.theme.AppTypography

/*
schermata Home
Come tutte le altre schermate, possiede un'istanza di navController e burgirViewModel
chosenCategoryId salva l'id della categoria scelta (cliccata dall'utente, default 1 -> burgers)
popularProducts e categories sono estratti dal viewModel
 */

@Composable
fun HomeScreen(
  navController: NavController,
  burgirViewModel: BurgirViewModel
) {

  // contiene l'id della categoria selezionata
  var chosenCategoryId by rememberSaveable { mutableStateOf(1) }

  // popularProduct sono i prodotto piu acquistati dall'utente (timesPurchased)
  burgirViewModel.getProductsByPopularity()

  // osserva i prodotti piu popolari e le categorie
  val popularProducts by burgirViewModel.products.observeAsState(emptyList())
  val categories by burgirViewModel.categories.observeAsState(emptyList())

  // identifica la struttura principale della pagina
  PrimaryScaffold(
    navController = navController,
    burgirViewModel = burgirViewModel,
  ) { innerPadding ->
    // crea una griglia verticale (nx2), funziona come una RecyclerView
    LazyVerticalGrid(
      columns = GridCells.Fixed(2),
      contentPadding = innerPadding,
      horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {

      // Label con "Hey, <nome>", occupa 2 posti
      item(span = { GridItemSpan(2) }) {
        Text(
          text = buildAnnotatedString {
            append("Hey, ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
              append(stringResource(id = R.string.profile_name))
            }
          },
          style = AppTypography.titleLarge,
          color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
          modifier = Modifier.padding(vertical = 10.dp)
        )
      }

      // Label con "Choose your best meal", occupa 2 posti
      item(span = { GridItemSpan(2) }) {
        Text(
          text = stringResource(R.string.home_screen_secondary),
          style = AppTypography.displayMedium.copy(fontWeight = FontWeight.ExtraBold),
        )
      }

      // slider delle categorie, al click di ogni categoria viene modificato il valore in chosenCategoryId
      // con quella selezionata
      item(span = { GridItemSpan(2) }) {
        CategorySlider(
          chosenCategoryId = chosenCategoryId,
          setChosenCategoryId = { newCategoryId -> chosenCategoryId = newCategoryId },
          categories = categories
        )
      }

      // Label "Popular in <categoria>", <categoria> e' preso da chosenCategoryId
      // (id delle categorie vanno da 1 a 5, mentre categories e' un array con valori negli indici
      // da 0 a 4, quindi e' necessario traslare i valori
      item(span = { GridItemSpan(2) }) {
        Text(
          text = buildAnnotatedString {
            val chosenCategory =
              if (categories.isEmpty()) null else categories[chosenCategoryId - 1]
            if (chosenCategory != null) {
              append(stringResource(R.string.home_screen_popular_in))
              // aggiunge il colore corrisponde alla categoria
              withStyle(
                style = SpanStyle(
                  color = Color.hsv(
                    hue = chosenCategory.categoryColor1 - 0.5f,
                    saturation = chosenCategory.categoryColor2,
                    value = chosenCategory.categoryColor3
                  ),
                  fontWeight = FontWeight.ExtraBold
                )
              ) {
                append(if (categories.isNotEmpty()) " ${categories[chosenCategoryId - 1].categoryName}" else "")
              }
            }
          },
          style = AppTypography.titleLarge.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onBackground,
          modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),
          textAlign = TextAlign.Center
        )
      }

      // ottieni tutti i prodotti piu popolari di una determinata categoria (chosenCategoryId)
      // ProductItem disegna la card per ogni prodotto
      items(
        popularProducts.filter { it.category == chosenCategoryId },
        key = { product -> product.id }) { product ->
        ProductItem(product, navController)
      }
    }
  }
}
