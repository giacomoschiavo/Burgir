package com.example.burgir.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(c: Cart)

    @Delete
    suspend fun delete(cart: Cart)

    @Update
    suspend fun update(cart:Cart)

    @Query("SELECT * FROM Cart")
    fun getAllCarts() : Flow<List<Cart>>
}