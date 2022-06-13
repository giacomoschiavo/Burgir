package com.example.burgir.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface CategoryDao {


    /**
     * Insert the specified category in the database
     */
    @Insert(onConflict=OnConflictStrategy.IGNORE)
    suspend fun insert(category: Category)

    /**
     * delete the specified category from the database
     */
    @Delete
    suspend fun delete(category: Category)

    /**
     * update the specified category in the database
     */
    @Update
    suspend fun update(category: Category)

    /**
     * return a Flow<List> of all the categories in the database
     */

    @Query("SELECT * FROM Category")
    fun getAllCategories() : Flow<List<Category>>

}