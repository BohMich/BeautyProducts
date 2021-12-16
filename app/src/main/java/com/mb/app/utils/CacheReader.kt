package com.mb.app.utils

import com.mb.app.data.offline.ProductDao
import com.mb.app.data.offline.ProductEntry
import com.mb.app.models.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CacheReader @Inject constructor(private var productDao: ProductDao) {

    suspend fun getProducts(): List<Product>{

        val data: List<ProductEntry> = productDao.getProducts()
        val products: MutableList<Product> = ArrayList()

        data.forEach {
            products.add(Product(it.id,it.name!!,it.image!!,it.description!!,it.price!!))
        }

        return products
    }

    suspend fun cacheProducts(products: List<Product>){

        return productDao.saveProducts(products)

    }
}