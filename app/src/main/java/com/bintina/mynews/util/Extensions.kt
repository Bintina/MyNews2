package com.bintina.mynews.util

import android.content.Context
import android.content.Context.*
import com.bintina.mynews.MyApp
import com.bintina.mynews.data.model.search.QueryDetails
import com.bintina.mynews.MyApp.Companion.FILE_NAME
import com.bintina.mynews.MyApp.Companion.newsSharedPref
import com.bintina.mynews.MyApp.Companion.notificationEndDate
import com.google.gson.Gson
import java.util.Calendar


//Shared Preference Methods.........................................................................
//--------------->>Pref.............................................................................
fun objectToPreference(context: Context, query: QueryDetails, PREFERENCE_NAME: String) {
    val queryJsonString = queryObjectToJson(context, query)

    newsSharedPref = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)
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
    val querySharedPreferences = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)
    val queryJsonString = querySharedPreferences.getString(PREFERENCE_NAME, "").toString()

    return queryJsonString
}

//Checked Boolean Filters............................................................................
fun getSelectedFilters(
    artsBoolean: Boolean,
    businessBoolean: Boolean,
    entreprenuersBoolean: Boolean,
    politicsBoolean: Boolean,
    sportsBoolean: Boolean,
    travelBoolean: Boolean
): String? {
    val filters = listOf("arts", "business", "entreprenuers", "politics", "sports", "travel")
    val booleanList = listOf(
        artsBoolean,
        businessBoolean,
        entreprenuersBoolean,
        politicsBoolean,
        sportsBoolean,
        travelBoolean
    )
    val trueFiltersList = filters.filterIndexed { index, _ -> booleanList[index] }

    return if (trueFiltersList.isNullOrEmpty()) {
        null
    } else {
        "news_desk(${trueFiltersList.joinToString(",")})"
    }}

//Default date methods..............................................................................
fun getStartDate() {
    val currentDate = Calendar.getInstance()

    currentDate.add(Calendar.YEAR, -5)

    val initialStartYear = currentDate.get(Calendar.YEAR)
    val initialStartMonth = currentDate.get(Calendar.MONTH)
    val initialStartDay = currentDate.get(Calendar.DAY_OF_MONTH)

    val selectedFiveYearStartDate =
        String.format("%d-%02d-%02d", initialStartYear, initialStartMonth, initialStartDay)
    MyApp.searchStartDate = selectedFiveYearStartDate

    //notification start date
    currentDate.add(Calendar.MONTH, -6)

    val initialNotificationStartYear = currentDate.get(Calendar.YEAR)
    val initialNotificationStartMonth = currentDate.get(Calendar.MONTH)
    val initialNotificationStartDay = currentDate.get(Calendar.DAY_OF_MONTH)

    val selectedSixMonthStartDate =
        String.format(
            "%d-%02d-%02d",
            initialNotificationStartYear,
            initialNotificationStartMonth,
            initialNotificationStartDay
        )
    MyApp.searchStartDate = selectedSixMonthStartDate

}

fun getEndDate() {
    val currentDate = Calendar.getInstance()
    val initialEndYear = currentDate.get(Calendar.YEAR)
    val initialEndMonth = currentDate.get(Calendar.MONTH)
    val initialEndDay = currentDate.get(Calendar.DAY_OF_MONTH)

    val selectedEndDate =
        String.format("%d-%02d-%02d", initialEndYear, initialEndMonth, initialEndDay)
    MyApp.searchEndDate = selectedEndDate
    notificationEndDate = selectedEndDate

}