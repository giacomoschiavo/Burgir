import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.example.burgir.R

@Immutable
data class Product(
  val id: Int = 0,
  val name: String = "No name",
  @DrawableRes
  val imageUrl: Int,
  val price: Long = 0,
  val categoryId: Int = 0,
  val description: String = ""
)

val products = listOf(
  Product(
    id = 0,
    name = "Insalata strana",
    imageUrl = R.drawable.burger,
    categoryId = 1,
  ),
  Product(
    id = 1,
    name = "Dolce buono",
    imageUrl = R.drawable.burger,
    categoryId = 2
  ),
  Product(
    id = 2,
    name = "Dolce buonissimo",
    imageUrl = R.drawable.burger,
    categoryId = 2
  ),
  Product(
    id = 3,
    name = "Burger",
    imageUrl = R.drawable.burger
  ),
  Product(
    id = 4,
    name = "Burger",
    imageUrl = R.drawable.burger
  ),
  Product(
    id = 5,
    name = "Burger",
    imageUrl = R.drawable.burger
  ),
  Product(
    id = 6,
    name = "Burger",
    imageUrl = R.drawable.burger
  ),
  Product(
    id = 7,
    name = "Burger",
    imageUrl = R.drawable.burger
  ),
  Product(
    id = 8,
    name = "Burger",
    imageUrl = R.drawable.burger
  )
)