import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.burgir.components.PrimaryScaffold
import com.example.burgir.data.BurgirViewModel
import com.example.burgir.navigation.RouteConfig
import com.example.burgir.ui.theme.AppTypography

/*
Schermata SearchScreen
Lista tutte le categorie di prodotti presenti nell'app.
Al click di ogni categoria, si viene portati nella pagina della categoria (CategoryScreen) corrispondente
La funzione di ricerca nella search bar non e' stata implementata
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
  navController: NavController,
  burgirViewModel: BurgirViewModel,
) {

  // salva il testo scritto nella search bar
  var searchText by remember { mutableStateOf("") }

  // osserva i cambiamenti nelle categorie in burgirViewModel
  val categories by burgirViewModel.categories.observeAsState(emptyList())

  // naviga nella pagina della categoria (CategoryScreen) con id corrispondente
  val navigateToCategory: (categoryId: Int) -> Unit = { categoryId ->
    navController.navigate("${RouteConfig.CATEGORY_SCREEN_ROUTE}/$categoryId") { launchSingleTop }
  }

  PrimaryScaffold(
    navController = navController,
    burgirViewModel = burgirViewModel,
  ) { innerPadding ->
    // corrisponde ad una lista con recyclerView, ogni Composable deve essere creato in una funzione
    // item (per singolo oggetto) o items (per lista di oggetti)
    LazyColumn(
      modifier = Modifier
        .padding(innerPadding)
    ) {
      item {
        // titolo "Discover New Flavors"
        Text(
          text = "Discover\nNew Flavors",
          style = AppTypography.displaySmall.copy(fontWeight = FontWeight.Bold),
          modifier = Modifier.padding(vertical = 10.dp)
        )
        // Search bar (funzione di ricerca non implementata)
        OutlinedTextField(
          value = searchText,
          onValueChange = { newValue -> searchText = newValue },
          placeholder = { Text(text = "Search") },
          leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = null) },
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
        )
        // testo "CATEGORIES"
        Text(
          text = "CATEGORIES",
          style = AppTypography.bodySmall.copy(fontWeight = FontWeight.W800),
          color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
          modifier = Modifier.padding(10.dp)
        )
      }

      // lista delle categorie, ogni categoria e' una card clickabile
      // items permette di renderizzare una lista di oggetti (e' raccomandato l'uso di id
      // identificativi per ogni elemento)
      items(categories, key = { category -> category.id }) { category ->
        Card(
          onClick = { navigateToCategory(category.id) },
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
          // classe Category contiene tre valori per il colore di ogni categoria:
          // hue, saturation e value
          colors = CardDefaults.cardColors(
            containerColor = Color.hsv(
              hue = category.categoryColor1,
              saturation = category.categoryColor2,
              value = category.categoryColor3
            ),
            contentColor = Color.White
          )
        ) {
          Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.heightIn(150.dp)
          ) {
            // testo con nome della categoria
            Text(
              text = category.categoryName,
              modifier = Modifier
                .weight(1f)
                .padding(25.dp),
              style = AppTypography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )
            // immagine "simbolo" della categoria
            Image(
              painter = painterResource(id = category.imageUri),
              contentDescription = null,
              modifier = Modifier
                .padding(30.dp)
                .weight(0.5f)
                .scale(2f)
            )
          }
        }
      }
    }

  }
}