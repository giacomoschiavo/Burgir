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
    imageRes = R.drawable.b_bigmac,
    color = Color.hsv(25f, 0.96f, 1f)
  ),
  CategoryUiState(
    id = 1,
    name = "Chickens",
    imageRes = R.drawable.c_mcchicken,
    color = Color.hsv(120f, 0.7f, 1f)
  ),
  CategoryUiState(
    id = 2,
    name = "Snacks",
    imageRes = R.drawable.s_mcnuggets,
    color = Color.hsv(45f, 0.96f, 1f)
  ),
  CategoryUiState(
    id = 3,
    name = "Ice Creams",
    imageRes = R.drawable.i_mcflurrybacio,
    color = Color.hsv(200f, 0.96f, 1f)
  ),
  CategoryUiState(
    id = 4,
    name = "Drinks",
    imageRes = R.drawable.d_cocacola_m,
    color = Color.hsv(285f, 0.96f, 1f)
  ),

)