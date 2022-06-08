package com.example.burgir.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

//TODO COMMENTARE
class  BurgirRepository(private val productDao: ProductDao, private val cartDao: CartDao, private val categoryDao: CategoryDao) {
    /**
     * NOTA PER IL PRESENTATORE
     * QUESTA LISTA RAPPRESENTA L'ATTUALE LISTA DI PRODOTTI CHE TI INTERESSA MOSTRARE NELL'UI.
     * PER I PRODUCT, CHIAMANDO LE VARIE FUNZIONI CHE LI FILTRA PER CATEGORIA/FAVORITI/POPOLARI/CARRELLO, QUESTA LISTA ANDRA' A CONTENERE SOLO QUELLI.
     * QUINDI PER RIAVERLI TUTTI BISOGNERA' RICHIAMARE getAllProducts
     *
     */
    var products : Flow<List<Product>> = productDao.getAllProducts()
    val categories : Flow<List<Category>> = categoryDao.getAllCategories()
    val carts : Flow<List<Cart>> = cartDao.getAllCarts()
    /**
     * CATEGORY METHODS
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertCategory(category: Category) {
        categoryDao.insert(category)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteCategory(category: Category) {
        categoryDao.delete(category)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateCategory(category: Category){
        categoryDao.update(category)
    }

    /**
     * CART METHODS
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertCart(cart: Cart){
        cartDao.insert(cart)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteCart(cart:Cart){
        cartDao.delete(cart)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateCart(cart: Cart){
        cartDao.update(cart)
    }

    /**
     * PRODUCT METHODS
     */

    /**
     * GET ACTUAL PRODUCTS
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertProduct(product: Product){
        productDao.insert(product)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteProduct(product: Product){
        productDao.delete(product)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateProduct(product: Product){
        productDao.update(product)
    }

    fun getAllProducts(){
        products=productDao.getAllProducts()
    }

    fun getProductsByPopularity(): Flow<List<Product>> {
        products = productDao.getProductsByPopularity()
        return products
    }

    fun getProductsByFavorite(){
        products=productDao.getProductsByFavorite()
    }

    fun getProductsByCategory(id: Int){
        products=productDao.getProductsByCategory(id)
    }

    fun getProductsInCart(){
        products=productDao.getProductsInCart()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun checkout(){
        productDao.checkout()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addToCart(id : Int){
        productDao.addToCart(id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun removeFromCart(id: Int){
        productDao.removeFromCart(id)
    }
}