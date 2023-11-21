package com.bintina.mynews.search.api

import android.app.Activity
import android.app.Application
import android.content.Context
import com.bintina.mynews.model.NewsResult
import com.bintina.mynews.util.Constants
import com.bintina.mynews.util.Constants.SEARCH_API_END
import com.bintina.mynews.util.Constants.SEARCH_END_URL
import com.bintina.mynews.util.MyApp.Companion.QUERY_TERM
import com.bintina.mynews.util.MyApp.Companion.formattedQuery
import com.bintina.mynews.util.MyApp.Companion.savedQuery
import com.bintina.mynews.util.preferenceToString
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface ApiClient {
   @GET(SEARCH_END_URL)
   suspend fun getNews(
       @QueryMap finalQuery: Map<String, String>
   ): NewsResult?
}