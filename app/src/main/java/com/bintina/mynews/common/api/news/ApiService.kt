package com.bintina.mynews.common.api.news

import com.bintina.mynews.common.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object for creating and providing an instance of the Retrofit API client.
 */
object ApiService {

    /**
     * Create and return an instance of the Retrofit API client.
     *
     * @return An instance of the Retrofit API client.
     */
    fun create(): ApiClient {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiClient::class.java)
    }

}