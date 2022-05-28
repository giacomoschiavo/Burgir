package com.example.burgir.screen

import BottomBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.burgir.R
import com.example.burgir.ui.theme.AppTypography
import com.example.burgir.ui.theme.BurgirTheme
import com.example.burgir.ui.theme.Shapes

@Composable
fun ProfileScreen(navController: NavController) {
  Scaffold(
    topBar = {
      CenterAlignedTopAppBar(
        title = { Text("Your profile", style = AppTypography.titleLarge) },
        navigationIcon = {
          IconButton(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back arrow")
          }
        }
      )
    },
    bottomBar = { BottomBar(navController = navController) }
  ) { innerPadding ->
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier
        .padding(vertical = innerPadding.calculateTopPadding(), horizontal = 20.dp)
        .fillMaxSize()
    ) {
      Image(
        painter = painterResource(id = R.drawable.profile_pic),
        contentDescription = "Profile picture",
        modifier = Modifier
          .size(200.dp)
          .clip(shape = androidx.compose.material3.Shapes.Full)
      )
      Text(
        text = "Shagon",
        style = AppTypography.displayMedium,
        modifier = Modifier.paddingFromBaseline(top = 30.dp)
      )
    }

  }

}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
  BurgirTheme() {
    ProfileScreen(navController = rememberNavController())
  }
}