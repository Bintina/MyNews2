package com.bintina.mynews.topstories.business.api

import com.bintina.mynews.util.Constants.TOP_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    fun create(): ApiClient {
        val retrofit = Retrofit.Builder()
            .baseUrl(TOP_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiClient::class.java)
    }
}