import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.example.burgir.R

@Immutable
data class Product(
  val id: Int = 0,
  val name: String = "No name",
  @DrawableRes
  val imageUrl: Int = R.drawable.burger,
  val price: Float = 12.34f,
  val categoryId: Int = 0,
  val sales: Int = 0,
  val description: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut ornare tortor quis sagittis mattis. In sit amet justo eget felis ultrices bibendum. Vivamus in commodo orci, eget faucibus urna. Nunc id felis vitae risus efficitur finibus.",
  var isFavorite: Boolean = false,
  var quantity: Int = 0
)


val products = listOf(
  Product(
    id = 0,
    name = "Bacon Fries",
    imageUrl = R.drawable.b_bronx_steakhouse,
    categoryId = 1,
    sales = 10,
    isFavorite = true
  ),
  Product(
    id = 1,
    name = "Wrap",
    imageUrl = R.drawable.burger,
    categoryId = 2,
    isFavorite = true
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
    imageUrl = R.drawable.burger,
    sales = 20,
  ),
  Product(
    id = 4,
    name = "Burger",
    imageUrl = R.drawable.burger
  ),
  Product(
    id = 5,
    name = "Burger",
    imageUrl = R.drawable.burger,
    isFavorite = true
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
  ),
  Product(
    id = 9,
    name = "Burger",
    imageUrl = R.drawable.burger
  ),
  Product(
    id = 10,
    name = "Burger",
    imageUrl = R.drawable.burger
  ),
  Product(
    id = 11,
    name = "Burger",
    imageUrl = R.drawable.burger
  )
)