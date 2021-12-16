package com.mb.app.data.offline

import androidx.room.*
import com.mb.app.models.Product

@Dao
interface ProductDao {

    @Transaction
    suspend fun saveProducts(products: List<Product>) {
        products.forEach {
            insertProduct(ProductEntry(it.id, it.name, it.image, it.description, it.price))
        }
    }

    @Transaction
    suspend fun getProducts(): List<ProductEntry> {
        return getAll()
    }

    @Query("SELECT * FROM ProductEntry")
    suspend fun getAll(): List<ProductEntry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntry)

}