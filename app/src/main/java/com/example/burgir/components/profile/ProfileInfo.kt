package com.example.burgir.components.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.burgir.R
import com.example.burgir.ui.theme.AppTypography
import com.example.burgir.ui.theme.Shapes

/*
Questo componente e' usato solo nella schermata di profilo.
Mostra immagine e nome profilo
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileInfo(modifier: Modifier = Modifier) {
  ElevatedCard(shape = androidx.compose.material3.Shapes.Full) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = modifier.padding(20.dp)
    ) {
      Image(
        painter = painterResource(id = R.drawable.profile_pic),
        contentDescription = stringResource(R.string.acc_profile_picture),
        modifier = Modifier
          .size(100.dp)
          .clip(shape = Shapes.large)
      )
      Text(
        text = stringResource(id = R.string.profile_name),
        style = AppTypography.headlineSmall,
        modifier = Modifier
          .paddingFromBaseline(top = 30.dp),
        color = MaterialTheme.colorScheme.onBackground
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun ProfileInfoPreview() {
  ProfileInfo()
}