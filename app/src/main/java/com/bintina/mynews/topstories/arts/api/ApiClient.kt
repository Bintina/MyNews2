package com.bintina.mynews.topstories.arts.api

import com.bintina.mynews.model.NewsResult
import com.bintina.mynews.util.Constants.ART_END_URL
import retrofit2.http.GET

interface ApiClient {
    @GET(ART_END_URL)
    suspend fun getArtStories(): NewsResult?
}