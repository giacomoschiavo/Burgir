package com.example.burgir.data

import android.app.Application

class BurgirApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { BurgirRoomDatabase.getDatabase(this) }
    val repository by lazy { BurgirRepository(database.productDao(),database.cartDao(),database.categoryDao()) }
}