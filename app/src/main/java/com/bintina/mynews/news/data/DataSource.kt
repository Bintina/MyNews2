package com.bintina.mynews.news.data

import android.util.Log
import com.bintina.mynews.model.news.News
import com.bintina.mynews.model.search.Doc
import com.bintina.mynews.news.api.ApiService
import com.bintina.mynews.util.Constants.API_KEY
import com.bintina.mynews.util.MyApp.Companion.CURRENT_NEWS_STATE
import com.bintina.mynews.util.MyApp.Companion.QUERY_TERM
import com.bintina.mynews.util.MyApp.Companion.savedQuery

object DataSource {
    suspend fun loadNews(): List<News?>? {
        val apiCall = ApiService.create()

        val response = when (CURRENT_NEWS_STATE) {
            0 -> apiCall.getTopStories()
            1 -> apiCall.getPopularNews()
            2 -> apiCall.getBusinessNews()
            3 -> apiCall.getArtStories()
            4 -> apiCall.getScienceStories()
            else -> {
                null
            }
        }

        //Filter Results..................................................
        val results = response?.results
        var parameterToCheckForNull = "section"

        val filteredForSection = results?.filterNot { News ->
            when (parameterToCheckForNull) {
                "section" -> News?.section.isNullOrBlank()
                else -> false
            }
        }

        parameterToCheckForNull = "subsection"
        val filteredForSubsection = filteredForSection?.filterNot { News ->
            when (parameterToCheckForNull) {
                "subsection" -> News?.subsection.isNullOrBlank()
                else -> false
            }
        }

        parameterToCheckForNull = "abstract"
        val filteredForAll = filteredForSubsection?.filterNot { News ->
            when (parameterToCheckForNull) {
                "abstract" -> News?.abstract.isNullOrBlank()
                else -> false
            }
        }
        val filteredList: List<News?>? = filteredForAll

        return if (filteredList != null && filteredList!!.isNotEmpty()) {
            filteredList
        } else {
            null
        }
    }

    suspend fun loadSearchResults(keyword: String?): List<Doc?> {

     /*   val queryMap = mapOf(
            QUERY_TERM to savedQuery,
            "API End Url" to API_KEY
        )*/
        val formattedQuery = if (keyword != null) {
            "q=$keyword&$API_KEY"
        } else {
            API_KEY
        }

        Log.d("SearchDataSourceLog", "query submitted is $formattedQuery")
        val apiCall = com.bintina.mynews.search.api.ApiService.create()
        val response = apiCall.getNews(formattedQuery)

        val results: List<Doc?> = response?.results

        Log.d("responseDataSource", "results has ${results?.size}")


        return if (results != null &&  results!!.isNotEmpty()) {
            Log.d("filteredListDataSourceLog", "has ${results.size} results" )
            results
        } else {
            emptyList()
        }
    }

}