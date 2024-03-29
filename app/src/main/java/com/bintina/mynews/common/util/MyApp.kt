package com.bintina.mynews.common.util

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.view.View
import com.bintina.mynews.R
import com.bintina.mynews.common.model.news.News
import com.bintina.mynews.common.model.search.Doc
import com.bintina.mynews.common.model.search.QueryDetails
import java.util.Date

/**
 * Custom Application class for MyApp.
 * GitHub link: https://github.com/Bintina/MyNews2.git
 */
class MyApp : Application() {

    companion object {

        // Application instance
        lateinit var instance: MyApp
            private set


        //Fragment State
        var CURRENT_NEWS_STATE = 0

        //Clicked Articles list
        val clickedArticles: MutableSet<String> = HashSet()

        //clicked news link
        var clickedNewsLink = ""

        //Date instances
        lateinit var currentDate: Date
        lateinit var notificationStartDate: Date
        lateinit var searchStartDate: Date
        lateinit var searchEndDate: Date

        //news variables
        var topStoriesList: List<News?> = emptyList()
        var popularNewsList: List<News?> = emptyList()
        var businessStoriesList: List<News?> = emptyList()
        var artStoriesList: List<News?> = emptyList()
        var scienceStoriesList: List<News?> = emptyList()

        //search variables
        var defaultSearchStartDate = ""
        var defaultSearchEndDate = ""
        var searchKeyword: String = ""
        var enteredSearchStartDate = ""
        var enteredSearchEndDate = ""
        var searchBooleanArts: Boolean = false
        var searchBooleanBusiness: Boolean = false
        var searchBooleanEntreprenuers: Boolean = false
        var searchBooleanPolitics: Boolean = false
        var searchBooleanSports: Boolean = false
        var searchBooleanTravel: Boolean = false


        //notification variables
        var notificationNewsList: List<Doc?> = emptyList()


        //notification Shared Preference Keys.
        const val FILE_NAME = "Query Preferences"
        const val QUERY_TERM = "Query Term"
        const val FILTERS = "Filters"
    }

    /**
     * Called when the application is starting. Initializes the application instance
     * and obtains a reference to the application's context.
     */
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}