import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.burgir.ui.theme.AppTypography

// questo componente compare nelle 4 schermate principali
@Composable
fun LogoWithCartTopAppBar(
  navController: NavController,
  scrollBehavior: TopAppBarScrollBehavior,
  showBadge: () -> Boolean,
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
      CartIconWithBadge(navController = navController, showBadge())
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
    { true }
  )
}