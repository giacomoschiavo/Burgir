package com.example.burgir.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Delete
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert(onConflict=OnConflictStrategy.IGNORE)
    suspend fun insert(category: Category)

    @Delete
    suspend fun delete(category: Category)

    @Query("SELECT * FROM Category WHERE ID= :id")
    fun getCategoryById(id: Int) : Flow<Category>

    @Query("SELECT * FROM Category")
    fun getAllCategories() : Flow<List<Category>>

}