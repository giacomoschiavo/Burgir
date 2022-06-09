import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.burgir.data.Category
import com.example.burgir.ui.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Category(
  category: Category,
  isChosen: Boolean,
  onCategoryClicked: (Int) -> Unit,
  modifier: Modifier = Modifier
) {

  val backgroundColor by animateColorAsState(
    targetValue = if (isChosen) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background,
    animationSpec = tween(durationMillis = 50, easing = FastOutSlowInEasing)
  )
  val scale by animateFloatAsState(
    targetValue = if (isChosen) 1.09f else 1.0f,
    animationSpec = spring(
      dampingRatio = Spring.DampingRatioHighBouncy,
      stiffness = Spring.StiffnessLow
    )
  )

  ElevatedCard(
    onClick = { onCategoryClicked(category.id) },
    modifier = modifier
      .size(90.dp)
      .scale(scale),
    colors = CardDefaults.elevatedCardColors(containerColor = backgroundColor),
  ) {
    Column(
      modifier = Modifier
        .padding(5.dp)
        .align(Alignment.CenterHorizontally),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Image(
        painter = painterResource(category.imageUri),
        contentDescription = null,
        modifier = Modifier.weight(1f)
      )
      Text(
        text = category.categoryName,
        style = if (isChosen) AppTypography.labelMedium.copy(fontWeight = FontWeight.Bold) else AppTypography.labelMedium,
        modifier = Modifier.padding(5.dp)
      )
    }
  }
}
