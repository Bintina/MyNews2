package com.bintina.mynews.common.data.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LifecycleCoroutineScope
import com.bintina.mynews.common.api.news.ApiService
import com.bintina.mynews.common.model.news.News
import com.bintina.mynews.common.model.search.Doc
import com.bintina.mynews.common.util.Constants.API_KEY
import com.bintina.mynews.common.util.MyApp.Companion.CURRENT_NEWS_STATE
import com.bintina.mynews.common.util.MyApp.Companion.artStoriesList
import com.bintina.mynews.common.util.MyApp.Companion.businessStoriesList
import com.bintina.mynews.common.util.MyApp.Companion.currentDate
import com.bintina.mynews.common.util.MyApp.Companion.notificationStartDate
import com.bintina.mynews.common.util.MyApp.Companion.popularNewsList
import com.bintina.mynews.common.util.MyApp.Companion.scienceStoriesList
import com.bintina.mynews.common.util.MyApp.Companion.topStoriesList
import com.bintina.mynews.common.util.filterNewsResult
import com.bintina.mynews.common.util.filterSearchResult
import com.bintina.mynews.common.util.getApiDates
import com.bintina.mynews.news.view.adapter.Adapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.util.Date

/**
 * Object responsible for handling data source operations (loading news and search results).
 */
object DataSource {


    suspend fun loadNewsLists(lifecycleScope: LifecycleCoroutineScope): Array<List<News?>> {

        //Launch coroutines concurrently
        val topDeferred = loadTopNews(lifecycleScope)
        val popularDeferred = loadPopularNews(lifecycleScope)
        val businessDeferred = loadBusinessNews(lifecycleScope)
        val artDeferred = loadArtNews(lifecycleScope)
        val scienceDeferred = loadScienceNews(lifecycleScope)

        // Wait for all coroutines to complete and get the results
        val topResult = topDeferred.await()
        val popularResult = popularDeferred.await()
        val businessResult = businessDeferred.await()
        val artResult = artDeferred.await()
        val scienceResult = scienceDeferred.await()

        return arrayOf(topResult, popularResult, businessResult, artResult, scienceResult)
    }

    suspend fun loadTopNews(lifecycleScope: LifecycleCoroutineScope): Deferred<List<News?>> =
        lifecycleScope.async(Dispatchers.IO) {

            val apiCall = ApiService.create()

            val response = apiCall.getTopStories()

            // Filter out results with null section, subsection or abstract
            val results = response?.results
            topStoriesList = filterNewsResult(results)
            Log.d("DataSourceLoadTop", "loaded ${topStoriesList.size}")

            return@async topStoriesList
        }

    suspend fun loadPopularNews(lifecycleScope: LifecycleCoroutineScope): Deferred<List<News?>> =
        lifecycleScope.async(Dispatchers.IO) {

            val apiCall = ApiService.create()

            val response = apiCall.getPopularNews()

            // Filter out results with null section, subsection or abstract
            val results = response?.results
            popularNewsList = filterNewsResult(results)
            Log.d("DataSourceLoadPop", "loaded ${popularNewsList.size}")

            return@async popularNewsList
        }

    suspend fun loadBusinessNews(lifecycleScope: LifecycleCoroutineScope): Deferred<List<News?>> =
        lifecycleScope.async(Dispatchers.IO) {

            val apiCall = ApiService.create()

            val response = apiCall.getBusinessNews()

            // Filter out results with null section, subsection or abstract
            val results = response?.results
            businessStoriesList = filterNewsResult(results)
            Log.d("DataSourceLoadBus", "loaded ${businessStoriesList.size}")

            return@async businessStoriesList
        }

    suspend fun loadArtNews(lifecycleScope: LifecycleCoroutineScope): Deferred<List<News?>> =
        lifecycleScope.async(Dispatchers.IO) {

            val apiCall = ApiService.create()

            val response = apiCall.getArtStories()

            // Filter out results with null section, subsection or abstract
            val results = response?.results
            artStoriesList = filterNewsResult(results)
            Log.d("DataSourceLoadArt", "loaded ${artStoriesList.size}")

            return@async artStoriesList
        }

    suspend fun loadScienceNews(lifecycleScope: LifecycleCoroutineScope): Deferred<List<News?>> =
        lifecycleScope.async(Dispatchers.IO) {

            val apiCall = ApiService.create()

            val response = apiCall.getScienceStories()

            // Filter out results with null section, subsection or abstract
            val results = response?.results
            scienceStoriesList = filterNewsResult(results)
            Log.d("DataSourceLoadSci", "loaded ${scienceStoriesList.size}")

            return@async scienceStoriesList
        }

    suspend fun loadNews(): List<News?> {

        val apiCall = ApiService.create()
        println("current news state is $CURRENT_NEWS_STATE")

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