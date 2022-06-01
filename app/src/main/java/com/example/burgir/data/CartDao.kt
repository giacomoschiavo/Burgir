package com.example.burgir.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CartDao {

    @Insert
    suspend fun insert(c: Cart)

    @Delete
    suspend fun delete(cart: Cart)

    @Update
    suspend fun update(cart:Cart)

    @Query("SELECT * FROM Cart")
    suspend fun getAllCarts() : List<Cart>
}