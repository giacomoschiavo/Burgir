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
import com.example.burgir.navigation.RouteConfig.routes.routesInfo
import com.example.burgir.ui.theme.AppTypography

/*
Questo componente rappresenta la barra di navigazione
Ci sono 4 routes principali: Home, Search, Favorite e Profile
Per navigare e' necessario navController e routesInfo (creato in RouteConfig)
 */
@Composable
fun MainNavigationBar(navController: NavController, modifier: Modifier = Modifier) {
  val backStackEntry by navController.currentBackStackEntryAsState()
  val currentRoute = backStackEntry?.destination?.route?.substringBefore('/')

  NavigationBar(
    modifier = modifier
  ) {
    // crea un'icona per ogni route in routesInfo
    routesInfo.forEachIndexed { _, item ->
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
          // naviga al route selezionato
          navController.navigate(item.route) {
            popUpTo(navController.graph.findStartDestination().id) {
              // salva lo stato della route precedente
              saveState = true
            }
            // una singola copia su tutto il back stack
            launchSingleTop = true
            // ripristina da saveState
            restoreState = true
          }
        }
      )
    }
  }
}