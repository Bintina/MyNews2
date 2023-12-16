package com.bintina.mynews.data

import android.content.Context
import android.net.Uri
import android.util.Log
import com.bintina.mynews.model.news.News
import com.bintina.mynews.model.search.Doc
import com.bintina.mynews.api.ApiService
import com.bintina.mynews.model.search.QueryDetails
import com.bintina.mynews.search.controller.SearchFragment
import com.bintina.mynews.util.Constants.API_KEY
import com.bintina.mynews.util.MyApp
import com.bintina.mynews.util.MyApp.Companion.CURRENT_NEWS_STATE
import com.bintina.mynews.util.MyApp.Companion.searchQueryObject
import com.bintina.mynews.util.queryPreferenceToObject
import java.text.SimpleDateFormat
import java.util.Locale

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

    suspend fun loadSearchResults(keyword: String?, startDate: String?, endDate: String?, filters: String?): List<Doc?> {

Log.d("DataSourceQuery","DataSourceQuery = $keyword")
Log.d("DataSourceFilter","DataSourceFilters = $filters")

val filterList = listOf<String>()
/*
        Log.d("SearchDataSourceLog", "query submitted is $query")
*/
        val apiCall = com.bintina.mynews.api.ApiService.create()
        val response = apiCall.getSearchedNews(keyword, filters, API_KEY)

        val results: List<Doc?>? = response?.results?.docs

        Log.d("responseDataSource", "results has ${results?.size}")


        var parameterToCheckForDate = "pub_date"
      var filteredForDate = results?.filter { doc ->
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

          val newsDate = dateFormat.parse(doc?.pubDate)
            val startDateObj = dateFormat.parse(startDate)
            val endDateObj = dateFormat.parse(endDate)

            newsDate in startDateObj..endDateObj
        }

        return if (filteredForDate != null &&  filteredForDate!!.isNotEmpty() ) {
            Log.d("filteredListDataSourceLog", "has ${filteredForDate.size} results" )
            filteredForDate
        } else {
            emptyList()
        }
    }

}