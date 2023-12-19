package com.bintina.mynews.search.api

import com.bintina.mynews.api.ApiClient
import com.bintina.mynews.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    fun create(): com.bintina.mynews.search.api.ApiClient {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(com.bintina.mynews.search.api.ApiClient::class.java)
    }

}