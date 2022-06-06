package com.example.burgir.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

//TODO COMMENTARE
class BurgirViewModel(private val repository: BurgirRepository) : ViewModel(){
    /**
     * NOTA PER IL PRESENTATORE
     * QUESTA LISTA RAPPRESENTA L'ATTUALE LISTA DI PRODOTTI CHE TI INTERESSA MOSTRARE NELL'UI.
     * PER I PRODUCT, CHIAMANDO LE VARIE FUNZIONI CHE LI FILTRA PER CATEGORIA/FAVORITI/POPOLARI/CARRELLO, QUESTA LISTA ANDRA' A CONTENERE SOLO QUELLI.
     * QUINDI PER RIAVERLI TUTTI BISOGNERA' RICHIAMARE getAllProducts
     *
     */


    val products : LiveData<List<Product>> =repository.products.asLiveData()
    val categories : LiveData<List<Category>> =repository.categories.asLiveData()
    val carts : LiveData<List<Cart>> = repository.carts.asLiveData()


    /**
     * Category methods
     */
    fun insertCategory(category: Category) = viewModelScope.launch {
        repository.insertCategory(category)
    }
    fun deleteCategory(category: Category) = viewModelScope.launch {
        repository.deleteCategory(category)
    }

    fun updateCategory(category: Category) = viewModelScope.launch {
        repository.updateCategory(category)
    }

    /**
     * Cart methods
     */
    fun insertCart(cart: Cart) = viewModelScope.launch {
        repository.insertCart(cart)
    }

    fun deleteCart(cart: Cart) = viewModelScope.launch {
        repository.deleteCart(cart)
    }

    fun updateCart(cart: Cart) = viewModelScope.launch {
        repository.updateCart(cart)
    }
    /**
     * Product methods
     */
    fun insertProduct(product: Product) = viewModelScope.launch {
        repository.insertProduct(product)
    }

    fun deleteProduct(product: Product) = viewModelScope.launch {
        repository.deleteProduct(product)
    }

    fun updateProduct(product: Product) = viewModelScope.launch {
        repository.updateProduct(product)
    }
    /**
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
    */
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
