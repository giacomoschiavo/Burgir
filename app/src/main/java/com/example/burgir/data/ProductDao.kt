package com.example.burgir.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductDao {
    /**
     * insert a new product in the database. If a product with the same ID is already in the database, it is replaced with the new one.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product)

    /**
     * delete a product from the database.
     */
    @Delete
    suspend fun delete(product: Product)

    /**
     * update a product of the database
     */
    @Update
    suspend fun update(product: Product)

    /**
     * Return a list of all products
     */
    @Query("SELECT * FROM Product ORDER BY name ASC")
    fun getAllProducts(): Flow<List<Product>>

    /**
     * Return a list of most popular products (max 6 products as default)
     */
    @Query("SELECT * FROM Product ORDER BY times_purchased DESC LIMIT 6")
    fun getProductsByPopularity() : Flow<List<Product>>

    /**
     * Return a list of all products that belong to the specify category
    */
    @Query("SELECT * FROM Product WHERE category= :id")
    fun getProductsByCategory(id: Int): Flow<List<Product>>

    /**
     * Return a list of products that are in the user's favorite list
     */
    @Query("SELECT * FROM Product WHERE Is_Favorite= :favorite")
    fun getProductsByFavorite(favorite: Boolean=true) : Flow<List<Product>>


    /**
     * Return a list of products that are in the cart at the moment
     */
    @Query("SELECT * FROM Product WHERE cart_quantity>0")
    fun getProductsInCart() : Flow<List<Product>>

    /**
     * Increase the number of times the product has been ordered and set cartQuantity = 0.
     * This action is performed for every product after a checkout.
     */
    @Query("UPDATE Product SET times_purchased= times_purchased + cart_quantity, cart_quantity = 0")
    suspend fun checkout()

    /**
     * Add a product to the cart by 1 quantity
     */
    @Query("UPDATE Product SET cart_quantity= cart_quantity + :quantity WHERE id= :id")
    suspend fun addToCart(id : Int,quantity : Int = 1)

    /**
     * Remove the quantity of the specified product (identified by ID)
     */
    @Query("UPDATE Product SET cart_quantity = cart_quantity-1 WHERE id= :id")
    suspend fun removeFromCart(id: Int)

    /**
     * Modify the favorite field of the specified product
     */
    @Query("UPDATE Product SET Is_Favorite=CASE WHEN Is_Favorite=1 THEN 0 WHEN Is_Favorite=0 THEN 1 END WHERE id= :id")
    suspend fun updateFavorite(id: Int)

    /**
     *
     */
    @Query("SELECT * FROM Product ORDER BY times_purchased DESC LIMIT 4")
    fun getPopularProductsByCategory(id: Int): Flow<List<Product>>

}