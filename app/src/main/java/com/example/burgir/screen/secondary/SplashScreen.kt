package com.example.burgir.screen.secondary

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.burgir.R
import com.example.burgir.navigation.RouteConfig
import com.example.burgir.ui.theme.AppTypography
import kotlinx.coroutines.delay

/*
Questa schermata rappresenta lo splash screen iniziale
Alla conclusione dell'animazione, l'utente verra' reindirizzato alla schermata Home
 */
@Composable
fun SplashScreen(navController: NavController) {
  val scale = remember { Animatable(0f) }

  LaunchedEffect(key1 = true) {
    scale.animateTo(
      targetValue = 0.7f,
      animationSpec = tween(
        durationMillis = 600,
        easing = {
          OvershootInterpolator(4f).getInterpolation(it)
        })
    )
    delay(800L)
    navController.navigate(RouteConfig.MENU_SCREEN_ROUTE) {
      popUpTo(RouteConfig.SPLASH_SCREEN_ROUTE) { inclusive = true }
    }
  }

  Surface() {
    Box(
      contentAlignment = Alignment.Center,
      modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.primary)
    ) {
      Text(
        stringResource(R.string.splash_screen_name),
        style = AppTypography.displayLarge,
        fontWeight = FontWeight.ExtraBold,
        color = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier.scale(scale.value)
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
  SplashScreen(navController = rememberNavController())
}