package com.example.burgir.data

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * The viewmodel is the class used by UI to interact with data
 * Its role is to manage data and provide it to UI based on the requests made by the UI
 * Many functions are executed by coroutines in order to avoid to overload the process
 * The view-model in this app follows the Model-View-ViewModel design pattern
 * The viewmodel uses the repository to access and operate the data store in the room database
 */


/**
 * Functions that return Flow<List> and LiveData<List> return lists with objects that can mutate their attributes during the user uses the app.
 * The UI has to observe these data to detect possible changes of their attributes
 */
class BurgirViewModel(private val repository: BurgirRepository) : ViewModel(){



    var products : LiveData<List<Product>> =repository.products.asLiveData()
    val categories : LiveData<List<Category>> =repository.categories.asLiveData()
    val carts : LiveData<List<Cart>> = repository.carts.asLiveData()


    /**
     * Category methods
     */

    /**
     * Insert the specified category in the database
     */
    fun insertCategory(category: Category) = viewModelScope.launch {
        repository.insertCategory(category)
    }

    /**
     * delete the specified category from the database
     */
    fun deleteCategory(category: Category) = viewModelScope.launch {
        repository.deleteCategory(category)
    }

    /**
     * update the specified category in the database
     */
    fun updateCategory(category: Category) = viewModelScope.launch {
        repository.updateCategory(category)
    }

    /**
     * Cart methods
     */

    /**
     * insert the specified cart in the database.
     */
    fun insertCart(cart: Cart) = viewModelScope.launch {
        repository.insertCart(cart)
    }

    /**
     * delete the specified cart from the database
     */
    fun deleteCart(cart: Cart) = viewModelScope.launch {
        repository.deleteCart(cart)
    }

    /**
     * update the specified cart in the database
     */
    fun updateCart(cart: Cart) = viewModelScope.launch {
        repository.updateCart(cart)
    }
    /**
     * Product methods
     */

    /**
     * insert a new product in the database. If a product with the same ID is already in the database, it is replaced with the new one.
     */
    fun insertProduct(product: Product) = viewModelScope.launch {
        repository.insertProduct(product)
    }
    /**
     * delete the specified product from the database.
     */
    fun deleteProduct(product: Product) = viewModelScope.launch {
        repository.deleteProduct(product)
    }

    /**
     * update the specified product of the database
     */
    fun updateProduct(product: Product) = viewModelScope.launch {
        repository.updateProduct(product)
    }

    /**
     * Return a list of most popular products (20 products as default)
     */
    fun getProductsByPopularity(): Flow<List<Product>> {
        repository.getProductsByPopularity()
        products = repository.products.asLiveData()
        return repository.products
    }

    /**
     * Return a list of all products available in the database
     */
    fun getAllProducts(): Flow<List<Product>> {
        repository.getAllProducts()
        products = repository.products.asLiveData()
        return repository.products
    }


    /**
     * Return a list of all products that belong to the specified category
     */
    fun getProductsByCategory(id:Int): Flow<List<Product>> {
        repository.getProductsByCategory(id)
        products=repository.products.asLiveData()
        return repository.products
    }

    /**
     * Return a list of products that are in the user's favorite list
     */
    fun getProductsByFavorite(): Flow<List<Product>> {
        repository.getProductsByFavorite()
        products=repository.products.asLiveData()
        return repository.products
    }

    /**
     * Return a list of products that are in the cart at the moment
     */
    fun getProductsinCart(): Flow<List<Product>> {
        repository.getProductsInCart()
        products=repository.products.asLiveData()
        return repository.products
    }

    /**
     * Checkout fucntion
     * Increase the number of times the product has been ordered and set cartQuantity = 0.
     * This action is performed for every product after a checkout.
     * Insert a new cart with the specified price given as input in the database
     * The cart will be visible in the profile page
     */
    fun checkout(price: Double)= viewModelScope.launch{
        repository.checkout()
        repository.insertCart(Cart(0,price))
    }

    /**
     * Add a product to the cart by the specified quantity (1 by default)
     */
    fun addToCart(id: Int)=viewModelScope.launch{
        repository.addToCart(id)
    }

    /**
     * Remove 1 quantity of the specified product (identified by ID)
     */
    fun removeFromCart(id: Int)=viewModelScope.launch{
        repository.removeFromCart(id)
    }

    /**
     * Modify the favorite field of the specified product
     */
    fun updateFavorite(id: Int) = viewModelScope.launch{
        repository.updateFavorite(id)
    }

    /**
     * remove all the quantity of the product from the cart, used when the quantity is >1 and the user wants to cancel it from the order
     */
    fun removeAllFromCartByProductId(id : Int) = viewModelScope.launch {
        repository.removeAllFromCartByProductId(id)
    }

    /**
     * return the single product
     */
    fun getProductById(id :Int) : Flow<Product>{
        return repository.getProductById(id)
    }
}

/**
 * View Model Factory class used to create a new instance of the database in mainActivity
 */
class BurgirViewModelFactory(private val repository: BurgirRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BurgirViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BurgirViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
