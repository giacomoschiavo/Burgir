package com.example.burgir.data

import androidx.lifecycle.LiveData
import androidx.room.*
//TODO COMMENTARE
@Dao
interface CategoryDao {

    @Insert(onConflict=OnConflictStrategy.IGNORE)
    suspend fun insert(category: Category)

    @Delete
    suspend fun delete(category: Category)

    @Update
    suspend fun update(category: Category)

    @Query("SELECT * FROM Category WHERE ID= :id")
    fun getCategoryById(id: Int) : Category

    @Query("SELECT * FROM Category")
    fun getAllCategories() : List<Category>

}