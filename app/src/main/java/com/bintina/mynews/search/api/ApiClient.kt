package com.bintina.mynews.search.api

import com.bintina.mynews.model.news.NewsResult
import com.bintina.mynews.model.search.SearchResult
import com.bintina.mynews.util.Constants.SEARCH_END_URL
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiClient {
   @GET(SEARCH_END_URL)
   suspend fun getNews(
       @Query("keyword")keyword: String

       ): SearchResult?
}