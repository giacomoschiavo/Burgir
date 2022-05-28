import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.burgir.MainActivity

@Composable
fun BottomBar(navController: NavController, modifier: Modifier = Modifier) {
  NavigationBar(
    modifier = modifier
  ) {
    NavigationBarItem(
      selected = true,
      onClick = {
        navController.navigate(MainActivity.MENU_SCREEN_ROUTE) {
          launchSingleTop = true
        }
      },
      icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = null) },
    )
    NavigationBarItem(
      selected = false,
      onClick = {
        navController.navigate(MainActivity.CART_SCREEN_ROUTE) {
          launchSingleTop = true
        }
      },
      icon = { Icon(imageVector = Icons.Filled.Search, contentDescription = null) },
    )
    NavigationBarItem(
      selected = false,
      onClick = {
        navController.navigate(MainActivity.CART_SCREEN_ROUTE) {
          launchSingleTop = true
        }
      },
      icon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = null) },
    )
    NavigationBarItem(
      selected = false,
      onClick = {
        navController.navigate(MainActivity.PROFILE_SCREEN_ROUTE) {
          launchSingleTop = true
        }
      },
      icon = { Icon(imageVector = Icons.Filled.Person, contentDescription = null) },
    )
  }
}