package com.bintina.mynews.util

import android.app.DatePickerDialog
import android.content.Context
import android.view.View
import android.widget.TextView
import com.bintina.mynews.util.MyApp.Companion.FILE_NAME
import com.bintina.mynews.util.MyApp.Companion.QUERY_TERM
import com.bintina.mynews.util.MyApp.Companion.newsJson
import com.bintina.mynews.util.MyApp.Companion.newsSharedPref
import com.bintina.mynews.util.MyApp.Companion.savedQuery
import java.util.Calendar



//Shared Preference Methods.........................................................................
fun stringToPreference(context: Context, string: String, PREFERENCE_NAME: String) {
       newsSharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
       val newsSharedPrefEditor = newsSharedPref.edit()


       newsSharedPrefEditor.putString(PREFERENCE_NAME, string).apply()
   }


   //------>>Object....................................................................................
   fun preferenceToString(context: Context, PREFERENCE_NAME: String): String {
       newsSharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

       newsJson = newsSharedPref.getString(PREFERENCE_NAME, "").toString()

       return newsJson.toString()
   }

//Fetch preferences for ApiClient
fun getSavedQuery(context: Context, PREFERENCE_NAME: String): String{
    savedQuery = preferenceToString(context, QUERY_TERM)

    return savedQuery
}