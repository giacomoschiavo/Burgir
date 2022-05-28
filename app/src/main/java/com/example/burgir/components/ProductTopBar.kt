import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.burgir.MainActivity

@Composable
fun ProductTopBar(navController: NavController) {
  SmallTopAppBar(
    modifier = Modifier,
    title = { Text(text = "") },
    navigationIcon = {
      IconButton(onClick = { navController.popBackStack() }) {
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back arrow")
      }
    },
    actions = {
      IconButton(onClick = { /*TODO*/ }) {
        Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorite")
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