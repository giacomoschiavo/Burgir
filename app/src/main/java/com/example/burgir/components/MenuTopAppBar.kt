import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.burgir.MainActivity
import com.example.burgir.R

@Composable
fun MenuTopAppBar(navController: NavController, modifier: Modifier = Modifier) {
  CenterAlignedTopAppBar(
    title = { Text(stringResource(id = R.string.app_name)) },
    actions = {
      IconButton(onClick = {
        navController.navigate(MainActivity.CART_SCREEN_ROUTE) { launchSingleTop = true }
      }) {
        Icon(
          imageVector = Icons.Filled.ShoppingCart,
          contentDescription = "Account Icon"
        )
      }
    },
    modifier = modifier
  )
}