package com.bintina.mynews.util

import android.content.SharedPreferences
import com.bintina.mynews.R

class MyApp {

    companion object{
        //Fragment State
        var newsFragmentState = 0

        //Shared Preference
        lateinit var newsFragmentString: String
        lateinit var newsJson: String
        lateinit var newsSharedPref: SharedPreferences
        const val FILE_NAME = "News Fragment Preferences"
        var CURRENT_NEWS_STATE = 0


        var formattedQuery = "placeholder query"

        //query and filter values
        /*lateinit var query: String
        val formattedQuery = "q=$query&api-key=$API_KEY"*/
    }
}