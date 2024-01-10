package com.bintina.mynews.common.api.search

import com.bintina.mynews.common.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object for creating and providing an instance of the Retrofit API client for search.
 */
object ApiService {
    /**
     * Create and return an instance of the Retrofit API client for search.
     *
     * @return An instance of the Retrofit API client for search.
     */
    fun create(): ApiClient {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiClient::class.java)
    }

}