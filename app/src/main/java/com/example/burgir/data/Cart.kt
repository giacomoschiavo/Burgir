package com.example.burgir.data


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import java.sql.Date

@Entity
data class Cart (
    /**
     * Cart ID. It stands for the order number
     */
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name= "ID") val id : Int,
    /**
     * price of the order placed
     */
    @ColumnInfo(name="price") val price : Int,
    /**
     * Day when the order is been placed
     */
    //TODO SALVARE LA DATA DELL'ORDINE OPPURE NO
    @ColumnInfo(name="date") val date : Date

)