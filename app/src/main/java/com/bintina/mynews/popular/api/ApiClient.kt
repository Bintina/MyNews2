package com.bintina.mynews.popular.api

import com.bintina.mynews.model.NewsResult
import com.bintina.mynews.util.Constants.POP_END_URL
import retrofit2.http.GET

interface ApiClient {
    @GET(POP_END_URL)
    suspend fun getPopularNews(): NewsResult?
}