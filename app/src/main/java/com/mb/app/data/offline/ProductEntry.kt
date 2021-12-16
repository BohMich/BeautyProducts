package com.mb.app.data.offline

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntry (

    @PrimaryKey val id: String,

    @ColumnInfo(name = "productName") val name: String?,

    @ColumnInfo(name = "productImage") val image: String?,

    @ColumnInfo(name = "productDescription") val description: String?,

    @ColumnInfo(name = "productPrice") val price: String?

    )