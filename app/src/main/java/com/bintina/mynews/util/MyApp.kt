package com.bintina.mynews.util

import android.app.Application
import android.content.SharedPreferences
import com.bintina.mynews.model.news.News
import com.bintina.mynews.model.search.QueryDetails
import com.bintina.mynews.util.Constants.API_KEY

class MyApp: Application() {

    companion object{

        lateinit var instance: MyApp
            private set


        //Fragment State
        var CURRENT_NEWS_STATE = 0


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

        lateinit var searchResults: List<News?>

        var newsInPositionUrl = ""

        //notification variables
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
}

}