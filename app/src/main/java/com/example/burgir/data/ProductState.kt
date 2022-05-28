import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.example.burgir.R
import kotlin.random.Random

@Immutable
data class Product(
  val id: Int = 0,
  val name: String = "No name",
  @DrawableRes
  val imageUrl: Int = R.drawable.burger,
  val price: String = String.format("%.2f", Random(1).nextFloat().coerceAtLeast(1f)),
  val categoryId: Int = 0,
  val sales: Int = 0,
  val description: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut ornare tortor quis sagittis mattis. In sit amet justo eget felis ultrices bibendum. Vivamus in commodo orci, eget faucibus urna. Nunc id felis vitae risus efficitur finibus. Integer vitae aliquet libero. Morbi non arcu sed tellus accumsan viverra ac porttitor orci.",
  var quantity: Int = 0
)

val products = listOf(
  Product(
    id = 0,
    name = "Insalata strana",
    imageUrl = R.drawable.burger,
    categoryId = 1,
    sales = 10,
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