import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.burgir.ui.theme.AppTypography
import com.example.burgir.ui.theme.BurgirTheme
import com.example.burgir.ui.theme.Shapes

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun ProductsGrid(
  chosenCategoryId: Int,
  navigateToProduct: (Int) -> Unit,
  modifier: Modifier = Modifier
) {

  CompositionLocalProvider(
    LocalOverScrollConfiguration provides null
  ) {
    LazyVerticalGrid(
      columns = GridCells.Fixed(2),
    ) {
      items(products.filter { product -> product.categoryId == chosenCategoryId }) { product ->
        ProductItem(product, navigateToProduct, modifier)
      }
    }
  }
}



@Preview(showBackground = true)
@Composable
fun ProductPreview() {
  BurgirTheme {
    ProductItem(products[0], {})
  }
}

@Preview(showBackground = true)
@Composable
fun ProductsGridPreview() {
  BurgirTheme {
    ProductsGrid(0, {})
  }
}


