package com.mb.app.data.network

import com.mb.app.constants.NetworkConstants
import com.mb.app.models.ProductSet
import retrofit2.Call
import retrofit2.http.GET

// External gate for communication with API endpoints (operated by Retrofit)
interface ApiClient {

    @GET(NetworkConstants.GET_ALL_PRODUCTS)
    fun getAllProducts(): Call<ProductSet?>?

}