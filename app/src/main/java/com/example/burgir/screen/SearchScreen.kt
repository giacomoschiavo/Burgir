import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.burgir.R
import com.example.burgir.ui.theme.BurgirTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController, modifier: Modifier = Modifier) {
  var searchText by rememberSaveable { mutableStateOf("") }
  LazyColumn(modifier = modifier.fillMaxSize()) {
    item {
      Text(text = "Discover\nNew Flavors")
      TextField(
        value = searchText,
        onValueChange = { newValue -> searchText = newValue },
        placeholder = { Text(text = "Search") },
        leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = null) }
      )
      Text(text = "Categories")
    }
    items(categories) { category ->
      OutlinedCard() {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(text = category.name)
          Image(painter = painterResource(id = category.imageRes), contentDescription = null)
        }
      }
    }
  }
}

@Preview
@Composable
fun SearchScreenPreview() {
  BurgirTheme() {
    SearchScreen(navController = rememberNavController())
  }
}