import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.burgir.ui.theme.BurgirTheme

@Composable
fun HomeScreen() {
  BurgirTheme {
    BurgirTopAppBar()
  }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BurgirTopAppBar(modifier: Modifier = Modifier) {
  Scaffold(
    modifier = modifier,
    topBar = {
      CenterAlignedTopAppBar(
        title = { Text("Burgir") },
        actions = {
          IconButton(onClick = {  }) {
            Icon(
              imageVector = Icons.Filled.AccountCircle,
              contentDescription = "Account Icon"
            )
          }
        },
      )
    },
    floatingActionButton = {
      ExtendedFloatingActionButton(
        onClick = {},
        text = { Text("Checkout") },
        icon = {
          Icon(
            imageVector = Icons.Filled.ShoppingCart,
            contentDescription = "Account Icon"
          )
        },
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary
      )
     },
    content = { innerPadding ->
      Column(modifier = modifier.padding(innerPadding)) {
        CategorySlider()
        Spacer(modifier = Modifier.size(30.dp))
        ProductsGrid()
      }
    },
    containerColor = MaterialTheme.colorScheme.background
  )
}


@Preview(showBackground = true, widthDp = 320, heightDp = 600)
@Composable
fun ScreenPreview() {
  BurgirTheme {
    BurgirTopAppBar()
  }
}