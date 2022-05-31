import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.burgir.R

data class CategoryUiState(
  val id: Int,
  val name: String = "Category $id",
  var chosen: Boolean = false,
  val color: Color,
  @DrawableRes val imageRes: Int
)

val categories = listOf(
  CategoryUiState(
    id = 0,
    name = "Burger",
    imageRes = R.drawable.burger,
    color = Color.hsv(25f, 0.96f, 1f)
  ),
  CategoryUiState(
    id = 1,
    name = "Wraps",
    imageRes = R.drawable.burger,
    color = Color.hsv(45f, 0.96f, 1f)
  ),
  CategoryUiState(
    id = 2,
    name = "Chickens",
    imageRes = R.drawable.burger,
    color = Color.hsv(150f, 0.96f, 1f)
  ),
  CategoryUiState(
    id = 3,
    name = "Ice Creams",
    imageRes = R.drawable.burger,
    color = Color.hsv(200f, 0.96f, 1f)
  ),
  CategoryUiState(
    id = 4,
    name = "Drinks",
    imageRes = R.drawable.burger,
    color = Color.hsv(285f, 0.96f, 1f)
  ),

)