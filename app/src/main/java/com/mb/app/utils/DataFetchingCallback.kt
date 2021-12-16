package com.mb.app.utils

interface DataFetchingCallback {

    fun fetchingDone(payload: Any)
    fun fetchingError()

}