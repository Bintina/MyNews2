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
        var CURRENT_NEWS_FRAGMENT = 0


        //News Array
        val arrayOfNewsFragments = arrayOf<Int>(
            R.string.top_fragment,
            R.string.pop_fragment,
            R.string.bus_fragment,
            R.string.art_fragment,
            R.string.sci_fragment,
        )
    }
}