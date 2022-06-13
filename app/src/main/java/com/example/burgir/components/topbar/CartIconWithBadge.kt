import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.burgir.navigation.RouteConfig

/*
Icona che mostra un badge rosso se il carrello contiene almeno un prodotto
 */
@Composable
fun CartIconWithBadge(navController: NavController, showBadge: Boolean = false) {
  IconButton(onClick = {
    navController.navigate(RouteConfig.CART_SCREEN_ROUTE) { launchSingleTop }
  }) {
    BadgedBox(badge = {
      AnimatedVisibility(visible = showBadge) {
        Badge(modifier = Modifier.size(10.dp))
      }
    }) {
      Icon(
        imageVector = Icons.Outlined.ShoppingCart,
        contentDescription = "Account Icon"
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun CartIconWithBadgePreview() {
  CartIconWithBadge(navController = rememberNavController(), true)
}