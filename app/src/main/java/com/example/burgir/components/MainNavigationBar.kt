import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.burgir.MainActivity
import com.example.burgir.ui.theme.AppTypography

data class RouteConfig(
  val label: String,
  val route: String,
  val filledIcon: ImageVector,
  val outlinedIcon: ImageVector
)

val routes = listOf(
  RouteConfig(
    "Home",
    MainActivity.MENU_SCREEN_ROUTE,
    filledIcon = Icons.Filled.Home,
    outlinedIcon = Icons.Outlined.Home
  ),
  RouteConfig(
    "Search",
    MainActivity.SEARCH_SCREEN_ROUTE,
    filledIcon = Icons.Filled.Search,
    outlinedIcon = Icons.Outlined.Search
  ),
  RouteConfig(
    "Favorite",
    MainActivity.FAVORITE_SCREEN_ROUTE,
    filledIcon = Icons.Filled.Favorite,
    outlinedIcon = Icons.Outlined.FavoriteBorder
  ),
  RouteConfig(
    "Profile",
    MainActivity.PROFILE_SCREEN_ROUTE,
    filledIcon = Icons.Filled.Person,
    outlinedIcon = Icons.Outlined.Person
  )
)

@Composable
fun MainNavigationBar(navController: NavController, modifier: Modifier = Modifier) {
  val backStackEntry by navController.currentBackStackEntryAsState()
  val currentRoute = backStackEntry?.destination?.route?.substringBefore('/')

  NavigationBar(
    modifier = modifier
  ) {
    routes.forEachIndexed { _, item ->
      val isSelected = currentRoute == item.route
      val fontStyle = AppTypography.labelMedium
      NavigationBarItem(
        icon = {
          Icon(
            if (isSelected) item.filledIcon else item.outlinedIcon,
            contentDescription = null
          )
        },
        label = {
          Text(
            item.label,
            style = if (isSelected) fontStyle.copy(fontWeight = FontWeight.Bold) else fontStyle
          )
        },
        selected = isSelected,
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