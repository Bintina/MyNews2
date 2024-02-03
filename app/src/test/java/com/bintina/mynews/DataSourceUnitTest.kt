package com.bintina.mynews


import com.bintina.mynews.common.api.news.ApiService
import com.bintina.mynews.common.data.DataSource
import com.bintina.mynews.common.model.news.Media
import com.bintina.mynews.common.model.news.MediaMetadata
import com.bintina.mynews.common.model.news.Multimedia
import com.bintina.mynews.common.model.news.News
import com.bintina.mynews.common.repository.MockNews
import com.bintina.mynews.common.util.filterNewsResult
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

class DataSourceTest {

    @Mock
    private lateinit var apiService: ApiService



    @Test
    fun `loadNews should return filtered news results`() = runBlocking {
        // Arrange
        val dataSource = DataSource
   val mockNews = MockNews(2)
        val expectedResults = mockNews.mockNewsList
val filteredMockNews = filterNewsResult(expectedResults)

                    // Assert
                    assertEquals(filterNewsResult(expectedResults).size, 1)
                }


        /*
            @Test
            fun `loadSearchResults should return filtered search results`() = runBlocking {
                // Arrange
                val dataSource = DataSource
                val keyword = "test"
                val startDate = Date()
                val endDate = Date()
                val filters = "filter1,filter2"
                val expectedResults = listOf(
                    Doc("Title 1", "Section 1", "Subsection 1"),
                    Doc("Title 2", "Section 2", "Subsection 2")
                )
                `when`(
                    apiService.getSearchedNews(
                        keyword,
                        filters,
                        true,
                        false,
                        any(),
                        any(),
                        "newest",
                        any()
                    )
                ).thenReturn(mockk {
                    `when`(results).thenReturn(mockk {
                        `when`(docs).thenReturn(expectedResults)
                    })
                })

                // Act
                val results = dataSource.loadSearchResults(keyword, startDate, endDate, filters)

                // Assert
                assertEquals(filterSearchResult(expectedResults), results)
            }

            @Test
            fun `loadNotificationResults should return filtered notification results`() = runBlocking {
                // Arrange
                val dataSource = DataSource
                val notificationKeyword = "notification"
                val filters = "filter1,filter2"
                val expectedResults = listOf(
                    Doc("Title 1", "Section 1", "Subsection 1"),
                    Doc("Title 2", "Section 2", "Subsection 2")
                )
                `when`(
                    apiService.getSearchedNews(
                        notificationKeyword,
                        filters,
                        true,
                        false,
                        any(),
                        any(),
                        "newest",
                        any()
                    )
                ).thenReturn(mockk {
                    `when`(results).thenReturn(mockk {
                        `when`(docs).thenReturn(expectedResults)
                    })
                })

                // Act
                val results = dataSource.loadNotificationResults(notificationKeyword, filters)

                // Assert
                assertEquals(filterSearchResult(expectedResults), results)
            }*/
    }