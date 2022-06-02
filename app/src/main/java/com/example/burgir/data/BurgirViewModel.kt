package com.example.burgir.data

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
//TODO COMMENTARE
class BurgirViewModel(private val repository: BurgirRepository): ViewModel(){
    /**
     * NOTA PER IL PRESENTATORE
     * QUESTA LISTA RAPPRESENTA L'ATTUALE LISTA DI PRODOTTI CHE TI INTERESSA MOSTRARE NELL'UI.
     * PER I PRODUCT, CHIAMANDO LE VARIE FUNZIONI CHE LI FILTRA PER CATEGORIA/FAVORITI/POPOLARI/CARRELLO, QUESTA LISTA ANDRA' A CONTENERE SOLO QUELLI.
     * QUINDI PER RIAVERLI TUTTI BISOGNERA' RICHIAMARE getAllProducts
     *
     */
    var products : LiveData<List<Product>> =repository.products
    val categories : List<Category> =repository.categories
    var carts : LiveData<List<Cart>> = repository.carts


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

    fun getCategoryById(id:Int) : Category{
        return repository.getCategoryById(id)
    }
    /**
     * Cart methods
     */
    fun insertCart(cart:Cart){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertCart(cart)
        }
    }

    fun deleteCart(cart:Cart){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteCart(cart)
        }
    }

    fun updateCart(cart:Cart){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateCart(cart)
        }
    }

    fun getAllCarts(){
        carts=repository.getAllCarts()
    }
    /**
     * Product methods
     */
    fun insertProduct(product:Product){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertProduct(product)
        }
    }

    fun deleteProduct(product:Product){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteProduct(product)
        }
    }

    fun updateProduct(product:Product){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateProduct(product)
        }
    }

    fun getProductById(id: Int) : LiveData<Product>{
        return repository.getProductById(id)
    }

    fun getAllProducts(){
        products=repository.getAllProducts()

    }

    fun getProductsByCategory(id: Int){
        repository.getProductsByCategory(id)
        products=repository.getActualProducts()
    }

    fun getProductsByFavorite(){
        repository.getProductsByFavorite()
        products=repository.getActualProducts()
    }

    fun getProductsByPopularity(){
        repository.getProductsByPopularity()
        products=repository.getActualProducts()
    }

    fun getProductsinCart(){
        repository.getProductsInCart()
        products=repository.getActualProducts()
    }


    fun checkout(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.checkout(id)
        }
    }

    fun addToCart(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.addToCart(id)
        }
    }

    fun removeFromCart(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.removeFromCart(id)
        }
    }

    fun size(): Int{
        return repository.size()
    }

    fun cartSize() : LiveData<Int>{
        return repository.cartSize()
    }
}
class BurgirViewModelFactory(private val repository: BurgirRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BurgirViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BurgirViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

