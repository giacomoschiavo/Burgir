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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
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
  LazyColumn {
    item {
      Text(
        text = "Discover\nNew Flavors",
        style = AppTypography.displaySmall.copy(fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(vertical = 10.dp)
      )
      OutlinedTextField(
        value = searchText,
        onValueChange = { newValue -> searchText = newValue },
        placeholder = { Text(text = "Search") },
        leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = null) },
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 20.dp)
      )
      Text(
        text = "CATEGORIES",
        style = AppTypography.bodySmall.copy(fontWeight = FontWeight.W800),
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
        modifier = Modifier.padding(10.dp)
      )
    }
    items(categories) { category ->
      Card(
        onClick = { navigateToCategory(category.id) },
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 10.dp),
        colors = CardDefaults.cardColors(
          containerColor = category.color,
          contentColor = Color.White
        )
      ) {
        Row(
          horizontalArrangement = Arrangement.Center,
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.height(100.dp)
        ) {
          Text(
            text = category.name,
            modifier = Modifier
              .weight(1f)
              .padding(25.dp),
            style = AppTypography.headlineSmall.copy(fontWeight = FontWeight.Bold)
          )
//          Image(
//            painter = painterResource(id = category.imageRes),
//            contentDescription = null,
//            modifier = Modifier
//              .padding(15.dp)
//              .scale(1.5f)
//          )
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