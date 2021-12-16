package com.mb.app.dependencyinjection

import com.mb.app.data.network.ApiClient
import com.mb.app.data.network.ApiClientBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule {

    @Provides
    @Singleton
    fun providesApiClient(): ApiClient {
        return ApiClientBuilder.apiClient()
    }
}