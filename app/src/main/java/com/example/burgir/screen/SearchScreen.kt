import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.burgir.components.PrimaryScaffold
import com.example.burgir.data.Category
import com.example.burgir.navigation.AppState
import com.example.burgir.ui.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(appState: AppState, categories: List<Category>, modifier: Modifier = Modifier) {
  var searchText by rememberSaveable { mutableStateOf("") }

  PrimaryScaffold(appState = appState, modifier = modifier) { innerPadding ->
    LazyColumn(
      modifier = Modifier
        .padding(innerPadding)
        .padding(horizontal = 20.dp)
    ) {
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
          onClick = { appState.navigateToCategory(category.id) },
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
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
            modifier = Modifier.height(100.dp)
          ) {
            Text(
              text = category.categoryName,
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
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
//    SearchScreen({})
}