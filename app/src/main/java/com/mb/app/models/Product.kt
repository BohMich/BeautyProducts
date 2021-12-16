package com.mb.app.models

import com.google.gson.annotations.SerializedName


open class Product (

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("price")
    val price: String

)