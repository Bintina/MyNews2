package com.bintina.mynews

import android.content.Context
import com.bintina.mynews.common.model.news.News
import com.bintina.mynews.common.repository.MockNews
import com.bintina.mynews.common.util.MyApp
import com.bintina.mynews.news.controller.OnNewsClickedListener
import com.bintina.mynews.news.view.adapter.Adapter
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test

class AdapterUnitTest {

    @MockK(relaxed = true)
    lateinit var context: Context

    @MockK(relaxed = true)
    lateinit var listener: OnNewsClickedListener

    private lateinit var adapter: Adapter

    @Before
    fun set_up() {
        MockKAnnotations.init(this)
        adapter = Adapter()
        adapter.listener = listener
        MyApp.clickedArticles.clear()
    }

    val mockNews = MockNews(2)
    val expectedResults = mockNews.mockNewsList

    @After
    fun tear_down() {
        clearAllMocks()
    }


    @Test
    fun get_item_count_should_return_correct_item_count() {

        adapter.storiesList = expectedResults

        val count = adapter.itemCount

        assert(count == 2)
    }

    @Test
    fun on_bind_view_holder_should_bind_data_to_view_holder() {
        val viewHolder: Adapter.ItemViewHolder = mockk(relaxed = true)
        val position = 0
        val news: News = mockk(relaxed = true)

        adapter.storiesList = listOf(news)

        adapter.onBindViewHolder(viewHolder, position)

        verify(exactly = 1) { viewHolder.bind(news, listener) }
    }

}