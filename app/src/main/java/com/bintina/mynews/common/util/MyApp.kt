package com.bintina.mynews.common.util

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.bintina.mynews.common.model.news.News
import com.bintina.mynews.common.model.search.QueryDetails
import java.util.Date

class MyApp: Application() {

    companion object{

        lateinit var instance: MyApp
            private set


        //Fragment State
        var CURRENT_NEWS_STATE = 0

        //Clicked Articles list
        val clickedArticles: MutableSet<String> = HashSet()

        //Shared Preference File Names
        const val FILE_NAME = "Query Preferences"
        const val QUERY_DETAILS = "Query Details"
        var searchQueryObject: QueryDetails? = null


        var savedQuery = ""
        var savedDateRange = ""
        var savedFilters = ""

        lateinit var newsFragmentString: String
        lateinit var newsJson: String
        lateinit var newsSharedPref: SharedPreferences

        //Date instances
        lateinit var currentDate: Date
        lateinit var notificationStartDate: Date
        lateinit var searchStartDate: Date
        lateinit var searchEndDate: Date

        //search variables
        var defaultSearchStartDate = "20181227"
        var defaultSearchEndDate = "20231227"
        var searchKeyword: String = ""
        var enteredSearchStartDate = ""
        var enteredSearchEndDate = ""
        var searchBooleanArts : Boolean = false
        var searchBooleanBusiness : Boolean = false
        var searchBooleanEntreprenuers : Boolean = false
        var searchBooleanPolitics : Boolean = false
        var searchBooleanSports : Boolean = false
        var searchBooleanTravel : Boolean = false
        lateinit var searchResults: List<News?>


        //notification variables
        var defaultNotificationStartDate = "20230627"
        var defaultNotificationEndDate = "20231227"
        var notificationKeyword: String = ""
        var notificationBooleanArts : Boolean = false
        var notificationBooleanBusiness : Boolean = false
        var notificationBooleanEntreprenuers : Boolean = false
        var notificationBooleanPolitics : Boolean = false
        var notificationBooleanSports : Boolean = false
        var notificationBooleanTravel : Boolean = false

    }
override fun onCreate(){
    super.onCreate()
    instance = this
    val myContext: Context = this
}

}