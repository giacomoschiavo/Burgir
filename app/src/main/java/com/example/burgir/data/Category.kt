package com.example.burgir.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity
data class Category (
    //TODO decidere se per category ID usiamo numero o nome
    /**
     * Category ID
     */
    @PrimaryKey @ColumnInfo(name= "ID") val id : Int,
    /**
     * category name
     */
    @ColumnInfo(name="name") val categoryName : String,
    /**
     * category color
     */
    @ColumnInfo(name= "color") val categoryColor : String
    //TODO come viene salvato il colore
)