package com.bintina.mynews.topstoriesapi.business.api

import com.bintina.mynews.model.NewsResult
import com.bintina.mynews.util.Constants.BUSINESS_END_URL
import retrofit2.http.GET

interface ApiClient {
    @GET(BUSINESS_END_URL)
    suspend fun getBusinessNews(): NewsResult?
}