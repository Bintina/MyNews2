package com.bintina.mynews


import com.bintina.mynews.common.api.news.ApiService
import com.bintina.mynews.common.data.DataSource
import com.bintina.mynews.common.model.news.Media
import com.bintina.mynews.common.model.news.MediaMetadata
import com.bintina.mynews.common.model.news.Multimedia
import com.bintina.mynews.common.model.news.News
import com.bintina.mynews.common.model.search.Doc
import com.bintina.mynews.repository.MockNews
import com.bintina.mynews.common.util.MyApp
import com.bintina.mynews.common.util.filterNewsResult
import com.bintina.mynews.common.util.getDefaultNotificationStartDate
import com.bintina.mynews.common.util.getSelectedFilters
import com.bintina.mynews.common.util.instantiateTodaysDate
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

class DataSourceTest {


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
        getDefaultNotificationStartDate(MyApp.currentDate)
        val keyword = "elections"
        val searchResults = dataSource.loadSearchResults(
            keyword,
            MyApp.notificationStartDate,
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
        val searchResults = dataSource.loadSearchResults(
            keyword,
            MyApp.notificationStartDate,
            MyApp.currentDate,
            getSelectedFilters(true, false, true, false, true, false)
        )
        assertTrue(searchResults is List<Doc?>)
    }

}