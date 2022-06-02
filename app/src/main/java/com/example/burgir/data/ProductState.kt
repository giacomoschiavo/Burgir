import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.example.burgir.R
import com.example.burgir.data.Product

//@Immutable
//data class Product(
//  val id: Int = 0,
//  val  productName = : String = "No  productName = ",
//  @ imageUrl = Res
//  val imageUrl: Int = R.drawable.burger,
//  val price: Double = 12.34,
//  val  category = : Int = 0,
//  val sales: Int = 0,
//  val description: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut ornare tortor quis sagittis mattis. In sit amet justo eget felis ultrices bibendum. Vivamus in commodo orci, eget faucibus urna. Nunc id felis vitae risus efficitur finibus.",
//  var isFavorite: Boolean = false,
//  var quantity: Int = 0
//)


val products = listOf(
  Product(
    id = 0,
    productName = "Bacon Fries",
    imageUrl = R.drawable.b_bronx_steakhouse,
    category = 1,
    discount = 10,
    isFavorite = true
  ),
  Product(
    id = 1,
    productName = "Wrap",
    imageUrl = R.drawable.burger,
    category = 2,
    isFavorite = true
  ),
  Product(
    id = 2,
    productName = "Dolce buonissimo",
    imageUrl = R.drawable.burger,
    category = 2
  ),
  Product(
    id = 3,
    productName = "Burger",
    imageUrl = R.drawable.burger,
    discount = 20,
    category = 0
  ),
  Product(
    id = 4,
    productName = "Burger",
    imageUrl = R.drawable.burger,
    category = 1
  ),
  Product(
    id = 5,
    productName = "Burger",
    imageUrl = R.drawable.burger,
    isFavorite = true,
    category = 0

  ),
  Product(
    id = 6,
    productName = "Burger",
    imageUrl = R.drawable.burger,
    category = 0

  ),
  Product(
    id = 7,
    productName = "Burger",
    imageUrl = R.drawable.burger,
    category = 0

  ),
  Product(
    id = 8,
    productName = "Burger",
    imageUrl = R.drawable.burger,
    category = 0

  ),
  Product(
    id = 9,
    productName = "Burger",
    imageUrl = R.drawable.burger,
    category = 0

  ),
  Product(
    id = 10,
    productName = "Burger",
    imageUrl = R.drawable.burger,
    category = 0

  ),
  Product(
    id = 11,
    productName = "Burger",
    imageUrl = R.drawable.burger,
    category = 0

  )
)