package com.mb.app.models

import com.google.gson.annotations.SerializedName

open class ProductSet (

    @SerializedName("products")
    val products: List<Product>

)