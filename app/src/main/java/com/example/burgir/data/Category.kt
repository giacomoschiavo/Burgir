package com.example.burgir.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    /**
     * Category ID
     */
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "ID") val id: Int,
    /**
     * category name
     */
    @ColumnInfo(name = "name") val categoryName: String,
    @ColumnInfo(name = "imageUrl") val imageUri: Int,
    /**
     * category color is represented as 3 float numbers. Instead of using TypeConverted, once the fragment gets the category, it can use the 3 floats to build the Color
     */
    @ColumnInfo(name = "color1") val categoryColor1: Float,
    @ColumnInfo(name = "color2") val categoryColor2: Float,
    @ColumnInfo(name = "color3") val categoryColor3: Float

)