package com.bintina.mynews.search.api

import android.app.Activity
import android.app.Application
import android.content.Context
import com.bintina.mynews.model.NewsResult
import com.bintina.mynews.util.MyApp.Companion.QUERY_TERM
import com.bintina.mynews.util.MyApp.Companion.formattedQuery
import com.bintina.mynews.util.preferenceToString
import retrofit2.http.GET


interface ApiClient {
/*
@GET(formattedQuery)
suspend fun getSearchResults(): NewsResult?
*/
}