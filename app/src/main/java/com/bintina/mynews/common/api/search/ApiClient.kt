package com.bintina.mynews.common.api.search

import com.bintina.mynews.common.model.search.SearchResult
import com.bintina.mynews.common.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Date

/**
 * Retrofit interface defining API endpoints for searching news data.
 */
interface ApiClient {
    /**
     * Fetches searched news using the specified parameters.
     *
     * @param query The search query string.
     * @param filter The filter string.
     * @param facet_filter Boolean indicating whether to include facet filters.
     * @param facet Boolean indicating whether to include facets.
     * @param begin_date The beginning date for the search.
     * @param end_date The end date for the search.
     * @param sort The sorting order.
     * @param apiKey The API key for authentication.
     *
     * @return A [SearchResult] object containing the search results.
     */
    @GET(Constants.SEARCH_END_URL)
    suspend fun getSearchedNews(
        @Query("q", encoded = true)query: String?,
        @Query("fq", encoded = true)filter: String?,
        @Query("facet_fields", encoded = true)facet_filter: Boolean?,
        @Query("facet", encoded = true)facet: Boolean?,
        @Query("begin_date", encoded = true)begin_date: String,
        @Query("end_date", encoded = true)end_date: String,
        @Query("sort", encoded = true)sort: String?,
        @Query("api-key")apiKey: String
    ): SearchResult?
}