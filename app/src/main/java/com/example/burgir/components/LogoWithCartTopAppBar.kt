import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.burgir.navigation.RouteConfig
import com.example.burgir.ui.theme.AppTypography

// questo componente compare nelle 4 schermate principali
@Composable
fun LogoWithCartTopAppBar(
  navController: NavController,
  scrollBehavior: TopAppBarScrollBehavior,
  modifier: Modifier = Modifier
) {
  CenterAlignedTopAppBar(
    title = {
      Text(
        "Burgir.",
        style = AppTypography.headlineMedium,
        fontWeight = FontWeight.ExtraBold,
        color = MaterialTheme.colorScheme.primary
      )
    },
    actions = {
      IconButton(onClick = {
        navController.navigate(RouteConfig.CART_SCREEN_ROUTE) { launchSingleTop = true }
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
    modifier = modifier,
    scrollBehavior = scrollBehavior
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LogoWithCartTopAppBarPreview() {
  LogoWithCartTopAppBar(
    rememberNavController(),
    TopAppBarDefaults.enterAlwaysScrollBehavior { true },
  )
}