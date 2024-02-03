package com.bintina.mynews.common.data

import android.util.Log
import android.widget.Toast
import com.bintina.mynews.common.api.news.ApiService
import com.bintina.mynews.common.model.news.News
import com.bintina.mynews.common.model.search.Doc
import com.bintina.mynews.common.util.Constants.API_KEY
import com.bintina.mynews.common.util.MyApp.Companion.CURRENT_NEWS_STATE
import com.bintina.mynews.common.util.MyApp.Companion.currentDate
import com.bintina.mynews.common.util.MyApp.Companion.notificationStartDate
import com.bintina.mynews.common.util.filterNewsResult
import com.bintina.mynews.common.util.filterSearchResult
import com.bintina.mynews.common.util.getApiDates
import java.util.Date

/**
 * Object responsible for handling data source operations (loading news and search results).
 */
object DataSource {
    /**
     * Loads news based on the current news state.
     *
     * @return List of [News] objects or null if no valid results.
     */
    suspend fun loadNews(): List<News?> {
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

        // Filter out results with null section, subsection or abstract
        val results = response?.results
        return filterNewsResult(results)
    }

    /**
     * Loads search results based on the specified parameters.
     *
     * @param keyword The search keyword.
     * @param startDate The start date for the search.
     * @param endDate The end date for the search.
     * @param filters Additional filters.
     *
     * @return List of [Doc] objects representing search results.
     */
    suspend fun loadSearchResults(
        keyword: String?,
        startDate: Date,
        endDate: Date,
        filters: String?
    ): List<Doc?> {


        //Format Dates to api date format
        val formattedStartDate = getApiDates(startDate, "yyyyMMdd")
        val formattedEndDate = getApiDates(endDate, "yyyyMMdd")

        val apiCall = com.bintina.mynews.common.api.search.ApiService.create()
        val response = apiCall.getSearchedNews(
            keyword,
            filters,
            true,
            false,
            formattedStartDate,
            formattedEndDate,
            "newest",
            API_KEY
        )

        val results: List<Doc?>? = response?.results?.docs


        //Filter out items with null section
        return filterSearchResult(results)
    }

    /**
     * Loads notification results based on the specified parameters.
     *
     * @param notificationKeyword The keyword for notifications.
     * @param filters Additional filters.
     *
     * @return List of [Doc] objects representing notification results.
     */
    suspend fun loadNotificationResults(
        notificationKeyword: String?,
        filters: String?
    ): List<Doc?> {

        val startDate = notificationStartDate

        val endDate = currentDate

        //Format dates
        val formattedStartDate = getApiDates(startDate, "yyyyMMdd")
        val formattedEndDate = getApiDates(endDate, "yyyyMMdd")

        val apiCall = com.bintina.mynews.common.api.search.ApiService.create()
        val response = apiCall.getSearchedNews(
            notificationKeyword,
            filters,
            true,
            false,
            formattedStartDate,
            formattedEndDate,
            "newest",
            API_KEY
        )

        val results: List<Doc?>? = response?.results?.docs

        //Filter out items with null section, subsection or image
        return filterSearchResult(results)
    }
}