import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.burgir.MainActivity
import com.example.burgir.ui.theme.BurgirTheme
import com.example.burgir.ui.theme.Shapes

@Composable
fun ProductTopBar(navController: NavController, showFavoriteIcon: Boolean = true) {
  SmallTopAppBar(
    modifier = Modifier,
    title = { Text(text = "") },
    colors = TopAppBarDefaults.smallTopAppBarColors(
      containerColor = MaterialTheme.colorScheme.background.copy(
        alpha = 0f
      )
    ),
    navigationIcon = {
      IconButton(onClick = { navController.popBackStack() }) {
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back arrow")
      }
    },
    actions = {
      if (showFavoriteIcon) {
        IconButton(onClick = { /*TODO*/ }) {
          Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorite")
        }
      }
      IconButton(onClick = {
        navController.navigate(MainActivity.CART_SCREEN_ROUTE) {
          launchSingleTop = true
        }
      }) {
        Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "Cart")
      }
    }
  )
}

@Preview(showBackground = true)
@Composable
fun ProductTopBarPreview() {
  BurgirTheme() {
    ProductTopBar(rememberNavController())
  }
}