package com.example.burgir.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    /**
     * insert the specified cart in the database. Operation used every time the user completes an order
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(c: Cart)

    /**
     * delete the specified cart from the database
     */
    @Delete
    suspend fun delete(cart: Cart)

    /**
     * update the specified cart in the database
     */
    @Update
    suspend fun update(cart:Cart)

    /**
     * return a Flow<List> of all the carts in the database
     */
    @Query("SELECT * FROM Cart")
    fun getAllCarts() : Flow<List<Cart>>
}