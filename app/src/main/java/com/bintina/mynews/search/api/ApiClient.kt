package com.bintina.mynews.search.api

import com.bintina.mynews.model.search.SearchResult
import com.bintina.mynews.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET(Constants.SEARCH_END_URL)
    suspend fun getSearchedNews(
        @Query("q", encoded = true)query: String?,
        @Query("fq", encoded = true)filter: String?,
        @Query("api-key")apiKey: String
    ): SearchResult?
}