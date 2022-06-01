package com.example.burgir.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
//TODO COMMENTARE
class BurgirRepository(private val productDao: ProductDao, private val cartDao: CartDao, private val categoryDao: CategoryDao) {
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

    suspend fun getCategoryById(id: Int) : Category{
         return categoryDao.getCategoryById(id);
    }

    suspend fun getAllCategories() : List<Category>{
        return categoryDao.getAllCategories()
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

    suspend fun getAllCarts() : List<Cart>{
        return cartDao.getAllCarts()
    }

    /**
     * PRODUCT METHODS
     */

    suspend fun insertProduct(product: Product){
        productDao.insert(product)
    }

    suspend fun deleteProduct(product: Product){
        productDao.delete(product)
    }

    suspend fun updateProduct(product: Product){
        productDao.update(product)
    }

    fun getProductById(id: Int): LiveData<Product>{
        return productDao.getProductById(id)
    }

    suspend fun getAllProducts(): List<Product>{
        return productDao.getAllProducts()
    }

    suspend fun getProductsByCategory(id: Int): List<Product>{
        return productDao.getProductsByCategory(id)
    }

    suspend fun getProductsByFavorite() : List<Product>{
        return productDao.getProductsByFavorite()
    }

    suspend fun getProductsByPopularity() : List<Product>{
        return productDao.getProductsByPopularity()
    }

    fun getProductsInCart() : LiveData<List<Product>>{
        return productDao.getProductsInCart()
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

    suspend fun size() : Int{
        return productDao.size()
    }

    fun cartSize() : LiveData<Int>{
        return productDao.cartSize()
    }
}