package com.bintina.mynews.common.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.bintina.mynews.common.model.search.QueryDetails
import com.bintina.mynews.common.util.MyApp.Companion.FILE_NAME
import com.bintina.mynews.common.util.MyApp.Companion.defaultNotificationEndDate
import com.bintina.mynews.common.util.MyApp.Companion.defaultSearchEndDate
import com.bintina.mynews.common.util.MyApp.Companion.newsSharedPref
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
    }
}

//Default date methods..............................................................................
fun getDefaultDates() {
//set end dates to current Date
    val currentDate = Calendar.getInstance()

    val endYear = currentDate.get(Calendar.YEAR)
    val endMonth = currentDate.get(Calendar.MONTH)
    val endDay = currentDate.get(Calendar.DAY_OF_MONTH)

    val defaultEndDate = String.format("", endYear, endMonth, endDay)

    defaultNotificationEndDate = defaultEndDate
    defaultSearchEndDate = defaultEndDate

    //Set default search start date
    currentDate.add(Calendar.YEAR, -5)

    val initialStartYear = currentDate.get(Calendar.YEAR)
    val initialStartMonth = currentDate.get(Calendar.MONTH)
    val initialStartDay = currentDate.get(Calendar.DAY_OF_MONTH)

    val selectedFiveYearStartDate =
        String.format("%d-%02d-%02d", initialStartYear, initialStartMonth, initialStartDay)
    MyApp.defaultSearchStartDate = selectedFiveYearStartDate

    //set default notification start date
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
    MyApp.defaultNotificationStartDate = selectedSixMonthStartDate

}
