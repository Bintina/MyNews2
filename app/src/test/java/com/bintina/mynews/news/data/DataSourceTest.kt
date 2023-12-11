package com.bintina.mynews.news.data

import com.bintina.mynews.model.news.News
import com.bintina.mynews.model.news.NewsResult
import org.junit.Assert.*
import org.junit.Before

class DataSourceTest {
//Attempting to write a test that checks that the DataSource methods return List<News?> values

    private lateinit var newsResponse: List<News?>

    @Before
    fun setUp(){
       newsResponse = mutableListOf<News>()
    }
}