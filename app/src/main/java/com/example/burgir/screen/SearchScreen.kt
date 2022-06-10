import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.burgir.components.PrimaryScaffold
import com.example.burgir.data.BurgirViewModel
import com.example.burgir.navigation.RouteConfig
import com.example.burgir.ui.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
  navController: NavController,
  burgirViewModel: BurgirViewModel,
) {
  var searchText by rememberSaveable { mutableStateOf("") }

  val categories by burgirViewModel.categories.observeAsState(emptyList())

  val navigateToCategory: (Int) -> Unit = {
    navController.navigate("${RouteConfig.CATEGORY_SCREEN_ROUTE}/$it") { launchSingleTop }
  }

  PrimaryScaffold(
    navController = navController,
    burgirViewModel = burgirViewModel,
  ) { innerPadding ->
    LazyColumn(
      modifier = Modifier
        .padding(innerPadding)
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
          onClick = { navigateToCategory(category.id) },
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
            modifier = Modifier.heightIn(150.dp)
          ) {
            Text(
              text = category.categoryName,
              modifier = Modifier
                .weight(1f)
                .padding(25.dp),
              style = AppTypography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )
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

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
  SearchScreen(rememberNavController(), burgirViewModel = viewModel())
}