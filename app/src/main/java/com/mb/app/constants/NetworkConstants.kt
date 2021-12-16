package com.mb.app.constants

class NetworkConstants {

    companion object {
        const val BASE_URL = "https://apps-tests.s3-eu-west-1.amazonaws.com/"
        const val GET_ALL_PRODUCTS = "android/products.json"

        // **************** Developer's Comment ****************
        // The image urls contained in the products.json do not point to valid images
        // The image url is still available, but for the purpose of this demo the url has been
        // replaced with a random .ico file found online.
        const val ALTERNATIVE_IMAGE = "https://iconarchive.com/download/i90538/icons8/windows-8/Ecommerce-Product.ico"
    }

}