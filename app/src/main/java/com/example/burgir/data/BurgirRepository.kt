package com.example.burgir.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
//TODO COMMENTARE
class BurgirRepository(private val productDao: ProductDao, private val cartDao: CartDao, private val categoryDao: CategoryDao) {
    /**
     * NOTA PER IL PRESENTATORE
     * QUESTA LISTA RAPPRESENTA L'ATTUALE LISTA DI PRODOTTI CHE TI INTERESSA MOSTRARE NELL'UI.
     * PER I PRODUCT, CHIAMANDO LE VARIE FUNZIONI CHE LI FILTRA PER CATEGORIA/FAVORITI/POPOLARI/CARRELLO, QUESTA LISTA ANDRA' A CONTENERE SOLO QUELLI.
     * QUINDI PER RIAVERLI TUTTI BISOGNERA' RICHIAMARE getAllProducts
     *
     */
    var products : LiveData<List<Product>> = productDao.getAllProducts()
    val categories : List<Category> = categoryDao.getAllCategories()
    val carts : LiveData<List<Cart>> = cartDao.getAllCarts()
    /**
     * CATEGORY METHODS
     */
    suspend fun insertCategory(category: Category) {
        categoryDao.insert(category)
    }

    suspend fun deleteCategory(category: Category) {
        categoryDao.delete(category)
    }

    suspend fun updateCategory(category: Category){
        categoryDao.update(category)
    }

    fun getCategoryById(id: Int) : Category{
         return categoryDao.getCategoryById(id);
    }
    /**
     * CART METHODS
     */
    suspend fun insertCart(cart: Cart){
        cartDao.insert(cart)
    }

    suspend fun deleteCart(cart:Cart){
        cartDao.delete(cart)
    }

    suspend fun updateCart(cart: Cart){
        cartDao.update(cart)
    }

    fun getAllCarts() : LiveData<List<Cart>>{
        return cartDao.getAllCarts()
    }
    /**
     * PRODUCT METHODS
     */

    /**
     * GET ACTUAL PRODUCTS
     */
    fun getActualProducts(): LiveData<List<Product>>{
        return products
    }
    suspend fun insertProduct(product: Product){
        productDao.insert(product)
    }

    suspend fun deleteProduct(product: Product){
        productDao.delete(product)
    }

    suspend fun updateProduct(product: Product){
        productDao.update(product)
    }

    fun getAllProducts(): LiveData<List<Product>>{
        return productDao.getAllProducts()
    }

    fun getProductById(id: Int): LiveData<Product>{
        return productDao.getProductById(id)
    }

    fun getProductsByCategory(id: Int): LiveData<List<Product>>{
        return productDao.getProductsByCategory(id)
    }

    fun getProductsByFavorite(){
        products=productDao.getProductsByFavorite()
    }

    fun getProductsByPopularity() {
        products = productDao.getProductsByPopularity()
    }

    fun getProductsInCart(){
        products=productDao.getProductsInCart()
    }

    suspend fun checkout(id:Int){
        productDao.checkout(id)
    }

    suspend fun addToCart(id : Int){
        productDao.addToCart(id)
    }

    suspend fun removeFromCart(id: Int){
        productDao.removeFromCart(id)
    }

    fun size() : Int{
        return productDao.size()
    }

    fun cartSize(): LiveData<Int>{
        return productDao.cartSize()
    }
}