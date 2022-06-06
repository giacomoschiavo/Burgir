import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.burgir.navigation.AppState.RouteConfig.routes
import com.example.burgir.ui.theme.AppTypography


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