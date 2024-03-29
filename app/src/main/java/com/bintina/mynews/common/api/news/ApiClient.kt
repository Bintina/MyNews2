package com.bintina.mynews.common.api.news

import com.bintina.mynews.common.model.news.NewsResult
import com.bintina.mynews.common.model.search.SearchResult
import com.bintina.mynews.common.util.Constants
import com.bintina.mynews.common.util.Constants.SEARCH_END_URL
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit interface defining API endpoints for fetching news data.
 */
interface ApiClient {
    //Popular News Client
    @GET(Constants.POP_END_URL)
    suspend fun getPopularNews(): NewsResult?

    //Top Stories Client
    @GET(Constants.TOP_END_URL)
    suspend fun getTopStories(): NewsResult?

    //Business Stories Client
    @GET(Constants.BUSINESS_END_URL)
    suspend fun getBusinessNews(): NewsResult?

    //Art Stories Client
    @GET(Constants.ART_END_URL)
    suspend fun getArtStories(): NewsResult?

    //Science Stories Client
    @GET(Constants.SCI_END_URL)
    suspend fun getScienceStories(): NewsResult?


}