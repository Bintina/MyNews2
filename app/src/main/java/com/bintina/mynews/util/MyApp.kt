package com.bintina.mynews.util

import android.content.SharedPreferences
import com.bintina.mynews.model.news.News
import com.bintina.mynews.model.search.QueryDetails
import com.bintina.mynews.util.Constants.API_KEY

class MyApp {

    companion object{
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

        var newsInPossitionUrl = ""
       //val var formattedQuery = "search/v2/articlesearch.json?q=$savedQuery&api-key=$API_KEY"
//https://api.nytimes.com/svc/search/v2/articlesearch.json?q=election&api-key=yourkey
        //query and filter values
        /*lateinit var query: String
        val formattedQuery = "q=$query&api-key=$API_KEY"*/
    }


}