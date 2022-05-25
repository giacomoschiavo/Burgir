import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CenterAlignedTopAppBar
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
    }) {
    Column {
      CategorySlider()
      Spacer(modifier = Modifier.size(30.dp))
      ProductsGrid()
    }

  }
}


@Preview(showBackground = true, widthDp = 320, heightDp = 600)
@Composable
fun ScreenPreview() {
  BurgirTheme {
    BurgirTopAppBar()
  }
}