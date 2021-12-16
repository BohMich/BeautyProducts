package com.mb.app.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mb.app.constants.LogTags
import com.mb.app.models.ProductSet
import com.mb.app.data.network.ApiClient
import com.mb.app.utils.DataFetchingCallback
import com.mb.app.utils.CacheReader
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ListFragmentViewModel @Inject constructor(private val apiClient: ApiClient,
                                                private val cacheReader: CacheReader)
    : ViewModel() {

    fun getProducts(callback: DataFetchingCallback) {

        apiClient.getAllProducts()
            ?.enqueue(object : Callback<ProductSet?> {

                override fun onResponse(
                    call: Call<ProductSet?>,
                    response: Response<ProductSet?>
                ) {
                    response.let {
                        if (it.isSuccessful) {

                            val results = it.body()!!

                            viewModelScope.launch {
                                cacheReader.cacheProducts(results.products)
                            }

                            callback.fetchingDone(results)
                        }
                    }
                }

                override fun onFailure(call: Call<ProductSet?>, t: Throwable) {
                    viewModelScope.launch {
                        val cache = cacheReader.getProducts()
                        if(cache.isEmpty()){
                            t.let {
                                Log.e(LogTags.NETWORK_ERROR, it.message.toString())
                                callback.fetchingError()
                            }
                        }
                        else{
                            callback.fetchingDone(cache)
                        }
                    }
                }
            })
    }
}