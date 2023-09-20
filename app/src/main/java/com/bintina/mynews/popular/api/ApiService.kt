package com.bintina.mynews.popular.api

import com.bintina.mynews.util.Constants.POP_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    fun create(): ApiClient{
        val retrofit = Retrofit.Builder()
            .baseUrl(POP_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiClient::class.java)
    }
}