package com.example.burgir.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert
    suspend fun insert(c: Cart)

    @Query("SELECT * FROM Cart")
    fun getAll() : Flow<List<Cart>>
}