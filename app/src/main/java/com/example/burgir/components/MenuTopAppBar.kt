import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.burgir.MainActivity
import com.example.burgir.R
import com.example.burgir.ui.theme.AppTypography

@Composable
fun MenuTopAppBar(navController: NavController, modifier: Modifier = Modifier) {
  CenterAlignedTopAppBar(
    title = { Text("🅱", style = AppTypography.titleLarge) },
    actions = {
      IconButton(onClick = {
        navController.navigate(MainActivity.CART_SCREEN_ROUTE) { launchSingleTop = true }
      }) {
        BadgedBox(badge = {
          Badge(modifier = Modifier.size(10.dp))
        }) {
          Icon(
            imageVector = Icons.Outlined.ShoppingCart,
            contentDescription = "Account Icon"
          )
        }
      }
    },
    modifier = modifier
  )
}