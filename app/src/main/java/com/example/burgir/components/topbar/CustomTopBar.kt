import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.burgir.R
import com.example.burgir.data.Product

/*
Usato solo da SecondaryScaffold, definisce una top app bar con un'icona carrello e preferiti (opzionali)
Se non viene passato alcun prodotto, non verra mostrata l'icona dei preferiti
Il carrello porta alla schermata del carrello ed e' definito in CartIconWithBadge
E' presente un back button
 */

@Composable
fun CustomTopBar(
  navController: NavController,
  scrollBehavior: TopAppBarScrollBehavior,
  modifier: Modifier = Modifier,
  showFavoriteIcon: Boolean = true,
  showCartIcon: Boolean = true,
  title: String = "",
  product: Product? = null,
  onFavoriteClick: (Product) -> Unit = {},
  showBadge: () -> Boolean = { false }
) {

  MediumTopAppBar(
    title = { Text(text = title) },
    navigationIcon = {
      // back button, fa pop dalla pila di navigazione
      IconButton(onClick = { navController.popBackStack() }) {
        Icon(
          imageVector = Icons.Filled.ArrowBack,
          contentDescription = stringResource(R.string.acc_back_button)
        )
      }
    },
    actions = {
      if (showFavoriteIcon && product != null) {
        var isFavorite by rememberSaveable { mutableStateOf(product.isFavorite) }
        val scale by animateFloatAsState(
          targetValue = if (isFavorite) 1.1f else 1.0f,
          animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessLow,
          )
        )
        // icona dei preferiti
        IconButton(modifier = Modifier.scale(scale), onClick = {
          onFavoriteClick(product)
          isFavorite = !isFavorite
        }) {
          Icon(
            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = stringResource(R.string.acc_favorite_icon)
          )
        }
      }
      // cart icon
      if (showCartIcon) {
        CartIconWithBadge(navController = navController, showBadge())
      }
    },
    scrollBehavior = scrollBehavior,
    modifier = modifier
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ProductTopBarPreview() {
  CustomTopBar(
    rememberNavController(),
    scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
  )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ProductTopBarWithoutBothPreview() {
  CustomTopBar(
    rememberNavController(),
    showFavoriteIcon = false,
    showCartIcon = false,
    scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
  )
}