package com.example.burgir.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

/**
 * This is the repository used by the view model to get access to data
 * This is not mandatory in a Room database implementation following the MVVM pattern but it is good practice to use it
 * The repository interact with data using Daos.
 */

class  BurgirRepository(private val productDao: ProductDao, private val cartDao: CartDao, private val categoryDao: CategoryDao) {
    /**
     * lists of all the entities in the database initialized using Daos
     *
     */

    /**
     * This list represent the products that the UI is interested to observe at that particular time.
     * Functions below modify it instead of returning a new Flow<List> of products every time the view model use them.
     */
    var products : Flow<List<Product>> = productDao.getAllProducts()
    val categories : Flow<List<Category>> = categoryDao.getAllCategories()
    val carts : Flow<List<Cart>> = cartDao.getAllCarts()
    /**
     * CATEGORY METHODS
     */

    /**
     * Insert the specified category in the database
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertCategory(category: Category) {
        categoryDao.insert(category)
    }

    /**
     * delete the specified category from the database
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteCategory(category: Category) {
        categoryDao.delete(category)
    }

    /**
     * update the specified category in the database
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateCategory(category: Category){
        categoryDao.update(category)
    }

    /**
     * CART METHODS
     */

    /**
     * insert the specified cart in the database. Operation used every time the user completes an order
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertCart(cart: Cart){
        cartDao.insert(cart)
    }

    /**
     * delete the specified cart from the database
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteCart(cart:Cart){
        cartDao.delete(cart)
    }

    /**
     * update the specified cart in the database
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateCart(cart: Cart){
        cartDao.update(cart)
    }

    /**
     * PRODUCT METHODS
     */

    /**
     * insert a new product in the database. If a product with the same ID is already in the database, it is replaced with the new one.
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertProduct(product: Product){
        productDao.insert(product)
    }

    /**
     * delete the specified product from the database.
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteProduct(product: Product){
        productDao.delete(product)
    }

    /**
     * update the specified product of the database
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateProduct(product: Product){
        productDao.update(product)
    }

    /**
     * Return a list of all products available in the database
     */
    fun getAllProducts(){
        products=productDao.getAllProducts()
    }

    /**
     * Assign to products a Flow<List> of most popular products (20 products as default)
     */
    fun getProductsByPopularity() {
        products = productDao.getProductsByPopularity()
    }

    /**
     * Assign to products a Flow<List> of products that are in the user's favorite list
     */
    fun getProductsByFavorite(){
        products=productDao.getProductsByFavorite()
    }

    /**
     * Assign to products a Flow<List> of all products that belong to the specified category
     */
    fun getProductsByCategory(id: Int){
        products=productDao.getProductsByCategory(id)
    }

    /**
     * Assign to products a Flow<List> of products that are in the cart at the moment
     */
    fun getProductsInCart(){
        products=productDao.getProductsInCart()
    }

    /**
     *Increase the number of times the product has been ordered and set cartQuantity = 0.
     * This action is performed for every product after a checkout.
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun checkout(){
        productDao.checkout()
    }

    /**
     * Add a product to the cart by the specified quantity (1 by default)
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addToCart(id : Int,quantity: Int=1){
        productDao.addToCart(id,quantity)
    }

    /**
     * Remove 1 quantity of the specified product (identified by ID)
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun removeFromCart(id: Int){
        productDao.removeFromCart(id)
    }

    /**
     * Modify the favorite field of the specified product
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateFavorite(id: Int) {
        productDao.updateFavorite(id)
    }

    /**
     * remove all the quantity of the product from the cart, used when the quantity is >1 and the user wants to cancel it from the order
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun removeAllFromCartByProductId(id: Int){
        productDao.removeAllFromCartByProductId(id)
    }

    /**
     * return the single product
     */
    fun getProductById(id :Int) : Flow<Product>{
        return productDao.getProductById(id)
    }
}