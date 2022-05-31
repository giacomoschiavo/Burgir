import android.util.Log
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.burgir.MainActivity
import com.example.burgir.ui.theme.BurgirTheme
import com.example.burgir.ui.theme.Shapes

@Composable
fun ProductTopBar(
  navController: NavController,
  scrollBehavior: TopAppBarScrollBehavior,
  showFavoriteIcon: Boolean = true,
  showCartIcon: Boolean = true,
  selectedCategoryId: Int = -1,
) {

  MediumTopAppBar(
    title = { Text(text = if (selectedCategoryId == -1) "" else categories.find { category -> category.id == selectedCategoryId }!!.name) },
    navigationIcon = {
      IconButton(onClick = { navController.popBackStack() }) {
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back arrow")
      }
    },
    actions = {
      if (showFavoriteIcon) {
        IconButton(onClick = { /*TODO*/ }) {
          Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "Favorite")
        }
      }
      if (showCartIcon) {
        IconButton(onClick = {
          navController.navigate(MainActivity.CART_SCREEN_ROUTE) {
            launchSingleTop = true
          }
        }) {
          Icon(imageVector = Icons.Outlined.ShoppingCart, contentDescription = "Cart")
        }
      }
    },
    scrollBehavior = scrollBehavior
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ProductTopBarPreview() {
  BurgirTheme() {
    ProductTopBar(
      rememberNavController(),
      scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ProductTopBarWithoutFavoritePreview() {
  BurgirTheme() {
    ProductTopBar(
      rememberNavController(),
      showFavoriteIcon = false,
      scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ProductTopBarWithoutBothPreview() {
  BurgirTheme() {
    ProductTopBar(
      rememberNavController(),
      showFavoriteIcon = false,
      showCartIcon = false,
      scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    )
  }
}