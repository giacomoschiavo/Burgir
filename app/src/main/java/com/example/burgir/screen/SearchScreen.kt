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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.burgir.R
import com.example.burgir.ui.theme.AppTypography
import com.example.burgir.ui.theme.BurgirTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navigateToCategory: (Int) -> Unit, modifier: Modifier = Modifier) {
  var searchText by rememberSaveable { mutableStateOf("") }
  LazyColumn(
    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    modifier = modifier
      .fillMaxSize()
  ) {
    item {
      Text(text = "Discover\nNew Flavors", style = AppTypography.displayMedium)
      Spacer(modifier = Modifier.size(20.dp))
      OutlinedTextField(
        value = searchText,
        onValueChange = { newValue -> searchText = newValue },
        placeholder = { Text(text = "Search") },
        leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = null) },
        modifier = Modifier.fillMaxWidth()
      )
      Spacer(modifier = Modifier.size(20.dp))
      Text(
        text = "CATEGORIES",
        style = AppTypography.bodySmall.copy(fontWeight = FontWeight.W800),
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
      )
    }
    items(categories) { category ->
      OutlinedCard(
        onClick = { navigateToCategory(category.id) },
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 10.dp, horizontal = 15.dp)
      ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
          Row(
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(text = category.name)
            Image(painter = painterResource(id = category.imageRes), contentDescription = null)
          }
        }

      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
  BurgirTheme() {
    SearchScreen({})
  }
}