package com.example.burgir.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Delete
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

//TODO DISCUTERE CON SCHIAVO SU COME GLI ARRIVANO I DATI, SE ATTRAVERSO SINGOLI VALORI O PASSANDO TUTTA L'ISTANZA

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
     * Return the product associated to the specified element
     */
    @Query("SELECT * FROM Product WHERE id= :id")
    fun getProductById(id: Int): Flow<Product>


    /**
     * Return a list of all products
     */
    @Query("SELECT * FROM Product ORDER BY name ASC")
    fun getAllProducts(): Flow<List<Product>>

    /**
     * Return a list of all products that belong to the specify category
     */
    //TODO verificare se passo category intera o solamente ID
    @Query("SELECT * FROM Product WHERE category= :id")
    fun getProductsByCategory(id: Int): Flow<List<Product>>

    /**
     * Return a list of products that are in the user's favorite list
     */
    @Query("SELECT * FROM Product WHERE Is_Favorited= :favorited")
    fun getProductsByFavorite(favorited: Boolean=true) : Flow<List<Product>>

    /**
     * Return a list of most popular products
     */
    //TODO DA DECIDERE SE SETTARE UN NUMERO PREDEFINITO OPPURE SE PORTARLI TUTTI IN ORDINE DI NUMERO DI VOLTE ACQUISTATE
    @Query("SELECT * FROM Product ORDER BY times_purchased DESC")
    fun getProductsByPopularity() : Flow<List<Product>>
}