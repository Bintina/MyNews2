package com.bintina.mynews

import android.content.Context
import com.bintina.mynews.util.MyApp.Companion.FILE_NAME
import com.bintina.mynews.util.MyApp.Companion.newsFragmentString
import com.bintina.mynews.util.MyApp.Companion.newsJson
import com.bintina.mynews.util.MyApp.Companion.newsSharedPref
import com.google.gson.Gson

class TempSpace {

    //News isnt changing like it does for Github.
    //Possible issue is the unification of the BASE_URL
    // or, the CURRENT_NEWS_FRAGMENT isnt changing vallue, this needs a log to check
    //



    /*fun fragToPreference(context: Context, newsFragmentState: Int, PREFERENCE_NAME: String) {
        newsSharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val newsSharedPrefEditor = newsSharedPref.edit()

        newsFragmentString = newsFragmentState.toString()

        newsSharedPrefEditor.putString(PREFERENCE_NAME, newsFragmentString).apply()
    }


    //------>>Object....................................................................................
    fun preferenceToFrag(context: Context, PREFERENCE_NAME: String): Int {
        newsSharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

        newsJson = newsSharedPref.getString(PREFERENCE_NAME, "").toString()

        return newsJson.toInt()
    }*/



}
//rerouting Science to top xml (recycler view)
//CRASH