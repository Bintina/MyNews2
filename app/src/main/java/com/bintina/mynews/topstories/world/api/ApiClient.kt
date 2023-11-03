package com.bintina.mynews.topstories.world.api

import com.bintina.mynews.model.NewsResult
import com.bintina.mynews.util.Constants.TOP_END_URL
import retrofit2.http.GET

interface ApiClient {
    @GET(TOP_END_URL)
    suspend fun getTopStories(): NewsResult?
}