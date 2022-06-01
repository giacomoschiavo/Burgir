package com.example.burgir.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BurgirViewModel(application: Application): AndroidViewModel(application){

    private val repository: BurgirRepository

    init{
        val productDao=BurgirRoomDatabase.getDatabase(application).productDao()
        val categoryDao=BurgirRoomDatabase.getDatabase(application).categoryDao()
        val cartDao=BurgirRoomDatabase.getDatabase(application).cartDao()

        repository= BurgirRepository(productDao,cartDao,categoryDao)
    }

    /**
     * Category methods
     */
    fun insertCategory(category: Category){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertCategory(category)
        }
    }
    fun deleteCategory(category: Category){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteCategory(category)
        }
    }
    fun updateCategory(category: Category){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateCategory(category)
        }
    }

}

