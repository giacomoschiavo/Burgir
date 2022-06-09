package com.example.burgir.data

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.Flow
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


    var products : LiveData<List<Product>> =repository.products.asLiveData()
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

    fun getProductsByPopularity(): Flow<List<Product>> {
        repository.getProductsByPopularity()
        products = repository.products.asLiveData()
        return repository.products
    }

    fun getAllProducts(): Flow<List<Product>> {
        repository.getAllProducts()
        products = repository.products.asLiveData()
        return repository.products
    }

    fun getProductsByCategory(id:Int): Flow<List<Product>> {
        repository.getProductsByCategory(id)
        products=repository.products.asLiveData()
        return repository.products
    }

    fun getProductsByFavorite(): Flow<List<Product>> {
        repository.getProductsByFavorite()
        products=repository.products.asLiveData()
        return repository.products
    }

    fun getProductsinCart(): Flow<List<Product>> {
        repository.getProductsInCart()
        products=repository.products.asLiveData()
        return repository.products
    }
    
    fun checkout()= viewModelScope.launch{
        repository.checkout()
    }

    fun addToCart(id: Int)=viewModelScope.launch{
        repository.addToCart(id)
    }


    fun removeFromCart(id: Int)=viewModelScope.launch{
        repository.removeFromCart(id)
    }

    fun updateFavorite(id: Int) = viewModelScope.launch{
        repository.updateFavorite(id)
    }

    fun removeAllFromCartByProductId(id : Int) = viewModelScope.launch {
        repository.removeAllFromCartByProductId(id)
    }

    fun getProductById(id :Int) : Flow<Product>{
        return repository.getProductById(id)
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
