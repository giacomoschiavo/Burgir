package com.example.burgir.data

import android.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity
data class Category (
    /**
     * Category ID
     */
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name= "ID") val id : Int,
    /**
     * category name
     */
    @ColumnInfo(name="name") val categoryName : String,
    /**
     * category color
     */
    @ColumnInfo(name= "color") val categoryColor : Int

)