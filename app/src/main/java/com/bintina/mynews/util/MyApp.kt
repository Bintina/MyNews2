package com.bintina.mynews.util

import android.content.SharedPreferences
import com.bintina.mynews.R

class MyApp {

    companion object{
        //Fragment State
        var CURRENT_NEWS_STATE = 0


        //Shared Preference
        const val FILE_NAME = "Query Preferences"
        const val QUERY_TERM = ""
        const val START_DATE = ""
        const val END_DATE = ""
        const val DATE_RANGE = ""
        const val FILTERS = ""


        lateinit var newsFragmentString: String
        lateinit var newsJson: String
        lateinit var newsSharedPref: SharedPreferences


        var formattedQuery = "placeholder query"

        //query and filter values
        /*lateinit var query: String
        val formattedQuery = "q=$query&api-key=$API_KEY"*/
    }
}