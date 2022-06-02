package com.example.burgir.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity
data class Product(
  /**
     * Product ID
     */
  @PrimaryKey(autoGenerate = true) val id: Int,

  /**
   * name of the product displayed to the user
   */
  @ColumnInfo(name = "name") val productName: String,
  /**
   * price of the product
   */

  @ColumnInfo(name = "price") val productPrice: Double = 12.34,
  /**
   * discount eventually applied to the product
   */

  @ColumnInfo(name = "discount") val discount: Int = 0,
  /**
   * description of the product
   */

  @ColumnInfo(name = "Product_Description") val description: String = "",
  /**
   * boolean value that tells if the product is a user's favorite
   */

  @ColumnInfo(name = "Is_Favorited") val isFavorite: Boolean = false,
  /**
   * number of times the user has purchased that specific element. (0 by default)
   */

  @ColumnInfo(name = "times_purchased") val timesPurchased: Int = 0,
  /**
     * Identifier of which category the product belongs
     */

    @ColumnInfo(name="category") val category: Int,
  /**
     * ImageUrl
     */

    @ColumnInfo(name="image_url") val imageUrl : Int,

  /**
   * number of times the product is added to the cart at the moment
   */
  @ColumnInfo(name = "cart_quantity") val cartQuantity: Int = 0


)