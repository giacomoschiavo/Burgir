import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PriceLabel(
  price: Double,
  modifier: Modifier = Modifier,
  style: TextStyle = TextStyle.Default,
  color: Color = MaterialTheme.colorScheme.onBackground
) {
  Row(modifier = modifier, verticalAlignment = Alignment.Top) {
    Text(
      text = "€",
      modifier = Modifier
        .scale(0.7f)
        .offset(y = -(2.dp)),
      style = style,
      color = color
    )
    Text(
      text = "%.2f".format(price),
      style = style.copy(fontWeight = FontWeight.SemiBold),
      color = color
    )
  }
}

@Preview(showBackground = true)
@Composable
fun PriceLabelPreview() {
//  PriceLabel(1.20f)
}