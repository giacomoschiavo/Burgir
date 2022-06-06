package com.example.burgir.data

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class BurgirApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { BurgirRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { BurgirRepository(database.productDao(),database.cartDao(),database.categoryDao()) }


}