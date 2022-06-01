import androidx.compose.foundation.layout.Row
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.burgir.ui.theme.AppTypography

@Composable
fun PriceLabel(
  price: Float,
  modifier: Modifier = Modifier,
  style: TextStyle = TextStyle.Default,
  color: Color = MaterialTheme.colorScheme.onBackground
) {
  Row(modifier = modifier, verticalAlignment = Alignment.Top) {
    Text(text = "€", modifier = Modifier.scale(0.7f), style = style, color = color)
    Text(text = "%.2f".format(price), style = style, color = color)
  }
}

@Preview(showBackground = true)
@Composable
fun PriceLabelPreview() {
  PriceLabel(1.20f)
}