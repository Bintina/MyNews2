package com.bintina.mynews.util

import android.content.SharedPreferences
import com.bintina.mynews.R
import com.bintina.mynews.util.Constants.API_KEY

class MyApp {

    companion object{
        //Fragment State
        var CURRENT_NEWS_STATE = 0


        //Shared Preference File Names
        const val FILE_NAME = "Query Preferences"
        const val QUERY_TERM = "Query Term"
        const val START_DATE = ""
        const val END_DATE = ""
        const val DATE_RANGE = "Date Range"
        const val FILTERS = "Filters"
        const val FORMATTED_QUERY = "Formatted Query"



        var savedQuery = ""
        var savedDateRange = ""
        var savedFilters = ""

        lateinit var newsFragmentString: String
        lateinit var newsJson: String
        lateinit var newsSharedPref: SharedPreferences


        var formattedQuery = "search/v2/articlesearch.json?q=$savedQuery&api-key=$API_KEY"
//https://api.nytimes.com/svc/search/v2/articlesearch.json?q=election&api-key=yourkey
        //query and filter values
        /*lateinit var query: String
        val formattedQuery = "q=$query&api-key=$API_KEY"*/
    }
}