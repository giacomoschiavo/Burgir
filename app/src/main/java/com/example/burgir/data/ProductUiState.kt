import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.example.burgir.R

@Immutable
data class ProductUiState(
  val id: Int = 0,
  val name: String = "No name",
  @DrawableRes
  val imageUrl: Int,
  val price: Long = 0,
  val category: Int = 0,
  val description: String = ""
)

val products = listOf(
  ProductUiState(
    id = 0,
    name = "Burger",
    imageUrl = R.drawable.burger
  ),
  ProductUiState(
    id = 0,
    name = "Burger",
    imageUrl = R.drawable.burger
  ),
  ProductUiState(
    id = 0,
    name = "Burger",
    imageUrl = R.drawable.burger
  ),
  ProductUiState(
    id = 0,
    name = "Burger",
    imageUrl = R.drawable.burger
  ),
  ProductUiState(
    id = 0,
    name = "Burger",
    imageUrl = R.drawable.burger
  ),
  ProductUiState(
    id = 0,
    name = "Burger",
    imageUrl = R.drawable.burger
  ),
  ProductUiState(
    id = 0,
    name = "Burger",
    imageUrl = R.drawable.burger
  ),
  ProductUiState(
    id = 0,
    name = "Burger",
    imageUrl = R.drawable.burger
  ),
  ProductUiState(
    id = 0,
    name = "Burger",
    imageUrl = R.drawable.burger
  )
)