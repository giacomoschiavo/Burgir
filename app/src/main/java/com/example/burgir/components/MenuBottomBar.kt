import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.burgir.MainActivity

data class RouteConfig(
  val label: String,
  val route: String,
  val imageVectorRef: ImageVector
)

val routes = listOf(
  RouteConfig("Home", MainActivity.MENU_SCREEN_ROUTE, Icons.Filled.Home),
  RouteConfig("Search", MainActivity.SEARCH_SCREEN_ROUTE, Icons.Filled.Search),
  RouteConfig("Favorite", MainActivity.FAVORITE_SCREEN_ROUTE, Icons.Filled.Favorite),
  RouteConfig("Profile", MainActivity.PROFILE_SCREEN_ROUTE, Icons.Filled.Person)
)

@Composable
fun BottomBar(navController: NavController, modifier: Modifier = Modifier) {
  val backStackEntry by navController.currentBackStackEntryAsState()
  val currentRoute = backStackEntry?.destination?.route?.substringBefore('/')

  NavigationBar(
    modifier = modifier
  ) {
    routes.forEachIndexed { index, item ->
      NavigationBarItem(
        icon = { Icon(item.imageVectorRef, contentDescription = null) },
        label = { Text(item.label) },
        selected = currentRoute == item.route,
        onClick = {
          navController.navigate(item.route) {
            popUpTo(navController.graph.findStartDestination().id) {
              saveState = true
            }
            launchSingleTop = true
            restoreState = true
          }
        }
      )
    }
  }
}