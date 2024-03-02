package com.bintina.mynews


import com.bintina.mynews.common.data.repository.DataSource
import com.bintina.mynews.common.model.search.Doc
import com.bintina.mynews.repository.MockNews
import com.bintina.mynews.common.util.MyApp
import com.bintina.mynews.common.util.filterNewsResult
import com.bintina.mynews.common.util.getDefaultNotificationStartDate
import com.bintina.mynews.common.util.getDefaultSearchStartDate
import com.bintina.mynews.common.util.getSelectedFilters
import com.bintina.mynews.common.util.instantiateTodaysDate
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class DataSourceTest {

    val sleepDuration: Long = 50000

    @Test
    fun datasource_results_filtered_for_null() = runBlocking {
        val mockNews = MockNews(2)
        val expectedResults = mockNews.mockNewsList
        val filteredMockNews = filterNewsResult(expectedResults)

        // Assert
        assertEquals(filteredMockNews.size, 1)
    }


    @Test
    fun load_search_method_returns_search_class() = runBlocking {
        val dataSource = DataSource
        instantiateTodaysDate()
        getDefaultSearchStartDate(MyApp.currentDate)
        val keyword = "elections"
        val searchResults = dataSource.loadSearchResults(
            keyword,
            MyApp.searchStartDate,
            MyApp.currentDate,
            getSelectedFilters(true, false, true, false, true, false)
        )
        assertTrue(searchResults is List<Doc?>)
    }

    @Test
    fun load_notification_method_returns_search_class() = runBlocking {
        val dataSource = DataSource
        instantiateTodaysDate()
        getDefaultNotificationStartDate(MyApp.currentDate)

        val keyword = "elections"
        val notificationResults = dataSource.loadNotificationResults(
            keyword,
            getSelectedFilters(true, false, true, false, true, false)
        )
        Thread.sleep(sleepDuration)
        assertTrue(notificationResults is List<Doc?>)
    }

}