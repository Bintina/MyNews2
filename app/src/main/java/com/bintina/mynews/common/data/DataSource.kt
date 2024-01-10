package com.bintina.mynews.common.data

import android.util.Log
import com.bintina.mynews.common.api.news.ApiService
import com.bintina.mynews.common.model.news.News
import com.bintina.mynews.common.model.search.Doc
import com.bintina.mynews.common.util.Constants.API_KEY
import com.bintina.mynews.common.util.MyApp.Companion.CURRENT_NEWS_STATE
import com.bintina.mynews.common.util.MyApp.Companion.currentDate
import com.bintina.mynews.common.util.MyApp.Companion.notificationStartDate
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

        // Filter out results with null section, subsection or abstract
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

        Log.d("DataSourceQuery", "DataSourceQuery = $keyword")
        Log.d("DataSourceFilter", "DataSourceFilters = $filters")
        Log.d("DataSourceDates", "before parsing startDate = $startDate & endDate = $endDate")

        //Format Dates to api date format
        val formattedStartDate = getApiDates(startDate, "yyyyMMdd")
        val formattedEndDate = getApiDates(endDate, "yyyyMMdd")

        Log.d(
            "DataSourceDates",
            "after parsing startDate = $formattedStartDate & endDate = $formattedEndDate"
        )

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

        Log.d("responseDataSource", "results has ${results?.size}")


        return if (results != null && results!!.isNotEmpty()) {
            Log.d("filteredListDataSourceLog", "has ${results.size} results")
            results
        } else {
            emptyList()
        }
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
        Log.d(
            "DatasourceNotificationLog",
            "before api call key = $notificationKeyword & filters = $filters"
        )
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
        Log.d(
            "DatasourceNotificationLog",
            "key = $notificationKeyword & filters = $filters, startDate = $startDate and endDate = $endDate"
        )
        val results: List<Doc?>? = response?.results?.docs

        //Filter out items with null section, subsection or image
        var parameterToCheckForNull = "section"
        val filteredForSection = results?.filterNot { Doc ->
            when (parameterToCheckForNull) {
                "section" -> Doc?.sectionName.isNullOrBlank()
                else -> false
            }
        }

        parameterToCheckForNull = "subsection"
        val filteredForSubsection = filteredForSection?.filterNot { Doc ->
            when (parameterToCheckForNull) {
                "subsection" -> Doc?.subsectionName.isNullOrBlank()
                else -> false
            }
        }

        parameterToCheckForNull = "abstract"
        val filteredForAbstract = filteredForSubsection?.filterNot { Doc ->
            when (parameterToCheckForNull) {
                "abstract" -> Doc?.abstract.isNullOrBlank()
                else -> false
            }
        }
        parameterToCheckForNull = "multimedia"
        val filteredForAll = filteredForAbstract!!.filterNot { Doc ->
            when (parameterToCheckForNull) {
                "multimedia" -> Doc?.multimedia!!.isEmpty()
                else -> false
            }
        }
        val filteredList: List<Doc?> = filteredForAll
        if (filteredList == null) {
            Log.d(
                "EmptyFilteredList",
                "You are all caught up. There is no recent news in your chosen categories."
            )
            // Toast.makeText(this,"You are all caught up. There is no recent news in your chosen categories.", Toast.LENGTH_LONG).Toast.Length
        }

        Log.d("responseDataSource", "results has ${filteredList?.size} after filter")
        return filteredList
    }
}