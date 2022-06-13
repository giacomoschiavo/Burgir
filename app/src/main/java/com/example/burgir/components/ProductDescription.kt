import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.burgir.R
import com.example.burgir.data.Product
import com.example.burgir.ui.theme.AppTypography

/*
Questo componente viene usato in ProductDetailsScreen
Mostra il prezzo del prodotto, l'eventuale prezzo scontato, il nome, la descrizione e un contatore
per indicare piu quantita dello stesso prodotto.
Cliccando il botton e' possibile aggiungere il prodotto nel carrello.
 */

@Composable
fun ProductDescription(
  product: Product?,
  onAddToCart: (productId: Int, quantity: Int) -> Unit,
  modifier: Modifier = Modifier
) {
  // salva il numero indicato dal contatore
  var quantity by rememberSaveable { mutableStateOf(1) }
  // salva se il button e' stato cliccato (animazione)
  var clicked by rememberSaveable() { mutableStateOf(false) }

  val scale by animateFloatAsState(
    targetValue = if (clicked) 1.04f else 1.0f,
    animationSpec = spring(
      dampingRatio = Spring.DampingRatioHighBouncy,
      stiffness = Spring.StiffnessLow
    )
  )

  if (product == null) {
    Text("No products found :(")
    return
  }
  Surface(
    modifier = modifier
  ) {
    val isDiscounted = product.discount > 0

    Column {
      // se in sconto mostra la percentuale
      if (isDiscounted) {
        Text(
          "${product.discount}% OFF",
          style = AppTypography.labelLarge,
          color = MaterialTheme.colorScheme.primary
        )
      }
      // mostra nome prodotto
      Text(
        text = product.productName,
        modifier = Modifier.padding(vertical = 10.dp),
        style = AppTypography.displaySmall
      )
      // mostra prezzo scontato
      if (isDiscounted) {
        PriceLabel(
          price = product.productPrice - (product.productPrice / 100 * product.discount),
          style = AppTypography.headlineMedium,
          color = MaterialTheme.colorScheme.primary
        )
      }
      // mostra prezzo originale (se scontato viene rimpicciolito e perde trasparenza)
      PriceLabel(
        price = product.productPrice,
        style = if (isDiscounted) AppTypography.titleMedium.copy(textDecoration = TextDecoration.LineThrough) else AppTypography.headlineMedium,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
      )
      // mostra descrizione
      Text(
        text = product.description,
        style = AppTypography.bodyMedium,
        modifier = Modifier
          .padding(vertical = 20.dp)
      )
      // contenitore per contatore e button
      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 10.dp)
      ) {
        QuantitySelector(
          onAdd = { quantity = it; clicked = false },
          onRemove = { quantity = it; clicked = false },
          modifier = Modifier
            .padding(horizontal = 10.dp)
            .weight(0.8f)
        )
        Button(
          onClick = { onAddToCart(product.id, quantity); clicked = true },
          modifier = Modifier
            .weight(1f)
            .padding(10.dp)
            .heightIn(60.dp)
            .scale(scale)
        ) {
          // anima transizione tra le due label
          // se non cliccato mostra "add to cart"
          AnimatedVisibility(visible = !clicked) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = "add to cart with cart icon"
              )
              Text(
                text = "Add to cart",
                style = AppTypography.labelLarge,
                modifier = Modifier.padding(10.dp)
              )
            }
          }
          // se cliccato dai feedback all'utente
          AnimatedVisibility(visible = clicked) {
            Icon(imageVector = Icons.Filled.Check, contentDescription = "check icon")
          }
        }
      }
    }
  }
}


@Preview(showBackground = true)
@Composable
fun ProductDescriptionDiscountedPreview() {
  ProductDescription(
    product = Product(
      0,
      "Hamburger",
      2.50,
      20,
      category = 0,
      imageUrl = R.drawable.b_bigmac
    ),
    onAddToCart = { _, _ -> }
  )
}

@Preview(showBackground = true)
@Composable
fun ProductDescriptionPreview() {
  ProductDescription(
    product = Product(
      0,
      "Hamburger",
      2.50,
      0,
      category = 0,
      imageUrl = R.drawable.b_bigmac
    ),
    onAddToCart = { _, _ -> }
  )
}