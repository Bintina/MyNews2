package com.bintina.mynews.topstories.science.api

import com.bintina.mynews.model.News
import com.bintina.mynews.model.NewsResult
import com.bintina.mynews.util.Constants.SCI_END_URL
import retrofit2.http.GET

interface ApiClient {
    @GET(SCI_END_URL)
    suspend fun getScienceStories(): NewsResult?
}