package com.bintina.mynews.data.network.api.search

import com.bintina.mynews.data.model.search.SearchResult
import com.bintina.mynews.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET(Constants.SEARCH_END_URL)
    suspend fun getSearchedNews(
        @Query("q", encoded = true)query: String?,
        @Query("fq", encoded = true)filter: String?,
        @Query("facet_fields", encoded = true)facet_filter: Boolean?,
        @Query("begin_date", encoded = true)begin_date: String?,
        @Query("end_date", encoded = true)end_date: String?,
        @Query("api-key")apiKey: String
    ): SearchResult?
}