import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.burgir.data.Product

@Composable
fun CustomTopBar(
  navController: NavController,
  scrollBehavior: TopAppBarScrollBehavior,
  showFavoriteIcon: Boolean = true,
  showCartIcon: Boolean = true,
  title: String = "",
  product: Product? = null,
  onFavoriteClick: (Product) -> Unit = {},
) {

  MediumTopAppBar(
    title = { Text(text = title) },
    navigationIcon = {
      IconButton(onClick = { navController.popBackStack() }) {
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back arrow")
      }
    },
    actions = {
      if (showFavoriteIcon && product != null) {
        var isFavorite by rememberSaveable { mutableStateOf(product.isFavorite) }
        IconButton(onClick = {
          onFavoriteClick(product)
          isFavorite = !isFavorite
        }) {
          Icon(
            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = "Favorite"
          )
        }
      }
      if (showCartIcon) {
        CartIconWithBadge(navController = navController, true)
      }
    },
    scrollBehavior = scrollBehavior
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ProductTopBarPreview() {
  CustomTopBar(
    rememberNavController(),
    scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ProductTopBarWithoutFavoritePreview() {
  CustomTopBar(
    rememberNavController(),
    showFavoriteIcon = false,
    scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ProductTopBarWithoutBothPreview() {
  CustomTopBar(
    rememberNavController(),
    showFavoriteIcon = false,
    showCartIcon = false,
    scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
  )
}