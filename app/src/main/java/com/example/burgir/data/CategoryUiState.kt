import androidx.annotation.DrawableRes
import com.example.burgir.R

data class CategoryUiState (
  val id: Int,
  val name: String = "Category $id",
  @DrawableRes val imageRes: Int
)

val categories = listOf(
  CategoryUiState(
    id = 0,
    name = "Panini",
    imageRes = R.drawable.burger
  ),
  CategoryUiState(
    id = 1,
    name = "Insalate",
    imageRes = R.drawable.burger
  ),
  CategoryUiState(
    id = 2,
    name = "Dolci",
    imageRes = R.drawable.burger
  ),
  CategoryUiState(
    id = 3,
    name = "Dessert",
    imageRes = R.drawable.burger
  ),
  CategoryUiState(
    id = 4,
    name = "Bevande",
    imageRes = R.drawable.burger
  ),
  CategoryUiState(
    id = 5,
    name = "Patatine",
    imageRes = R.drawable.burger
  ),
  CategoryUiState(
    id = 6,
    name = "Sfiziosità",
    imageRes = R.drawable.burger
  ),

)