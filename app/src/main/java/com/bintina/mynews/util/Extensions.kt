package com.bintina.mynews.util

import android.app.DatePickerDialog
import android.content.Context
import android.view.View
import android.widget.TextView
import com.bintina.mynews.model.search.QueryDetails
import com.bintina.mynews.util.MyApp.Companion.FILE_NAME
import com.bintina.mynews.util.MyApp.Companion.newsJson
import com.bintina.mynews.util.MyApp.Companion.newsSharedPref
import com.bintina.mynews.util.MyApp.Companion.savedQuery
import com.google.gson.Gson
import java.util.Calendar


//Shared Preference Methods.........................................................................
//--------------->>Pref.............................................................................
fun objectToPreference(context: Context, query: QueryDetails, PREFERENCE_NAME: String) {
    val queryJsonString = queryObjectToJson(context, query)

    newsSharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    val newsSharedPrefEditor = newsSharedPref.edit()


    newsSharedPrefEditor.putString(PREFERENCE_NAME, queryJsonString).apply()
}

fun queryObjectToJson(context: Context, query: QueryDetails?): String {
    val queryJsonString = Gson().toJson(query)

    return queryJsonString
}

//------>>Object....................................................................................
fun queryPreferenceToObject(context: Context, PREFERENCE_NAME: String): QueryDetails? {
    val queryJsonString = queryPreferenceToJson(context, PREFERENCE_NAME)

    val queryObject = Gson().fromJson<QueryDetails>(queryJsonString, QueryDetails::class.java)

    return queryObject
}

fun queryPreferenceToJson(context: Context, PREFERENCE_NAME: String): String {
    val querySharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    val queryJsonString = querySharedPreferences.getString(PREFERENCE_NAME, "").toString()

    return queryJsonString
}


