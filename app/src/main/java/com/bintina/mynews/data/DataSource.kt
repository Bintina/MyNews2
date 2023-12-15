package com.bintina.mynews.data

import android.content.Context
import android.util.Log
import com.bintina.mynews.model.news.News
import com.bintina.mynews.model.search.Doc
import com.bintina.mynews.api.ApiService
import com.bintina.mynews.model.search.QueryDetails
import com.bintina.mynews.util.Constants.API_KEY
import com.bintina.mynews.util.MyApp
import com.bintina.mynews.util.MyApp.Companion.CURRENT_NEWS_STATE
import com.bintina.mynews.util.MyApp.Companion.searchQueryObject
import com.bintina.mynews.util.queryPreferenceToObject

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

    suspend fun loadSearchResults(): List<Doc?> {
val query = searchQueryObject?.query
        val startDate = searchQueryObject?.startDate
        val endDate = searchQueryObject?.endDate
        val checkedFilters = searchQueryObject?.checked
        


        Log.d("SearchDataSourceLog", "query submitted is $query")
        val apiCall = com.bintina.mynews.api.ApiService.create()
        val response = apiCall.getSearchedNews(query, checkedFilters, API_KEY)

        val results: List<Doc?>? = response?.results?.docs

        Log.d("responseDataSource", "results has ${results?.size}")


        return if (results != null &&  results!!.isNotEmpty()) {
            Log.d("filteredListDataSourceLog", "has ${results.size} results" )
            results
        } else {
            emptyList()
        }
    }
    private fun addNewsDeskFilters(context: Context): String? {
        val artObject = getQueryFilterObject(context, MyApp.KEY_ARTS_PREF)

        val filterValue = if (artObject?.checked == false) {
            null
        } else {
            "news_desk(\"${artObject?.filterName}\")"
        }

        return filterValue
    }

    fun getQueryFilterObject(context: Context, PREFERENCE_NAME: String): QueryDetails? {
        val queryObject = queryPreferenceToObject(context, PREFERENCE_NAME)
        return queryObject
    }
}