package com.bintina.mynews.util

import android.content.Context
import com.bintina.mynews.util.MyApp.Companion.FILE_NAME
import com.bintina.mynews.util.MyApp.Companion.newsFragmentString
import com.bintina.mynews.util.MyApp.Companion.newsJson
import com.bintina.mynews.util.MyApp.Companion.newsSharedPref

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