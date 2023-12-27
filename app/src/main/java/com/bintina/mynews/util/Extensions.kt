package com.bintina.mynews.util

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.*
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.bintina.mynews.R
import com.bintina.mynews.model.search.QueryDetails
import com.bintina.mynews.notifications.controller.NotificationDisplayActivity
import com.bintina.mynews.notifications.work.NotificationWorker
import com.bintina.mynews.util.Constants.CHANNEL_ID
import com.bintina.mynews.util.Constants.NOTIFICATION_CHANNEL_DESCRIPTION
import com.bintina.mynews.util.Constants.NOTIFICATION_CHANNEL_NAME
import com.bintina.mynews.util.Constants.NOTIFICATION_ID
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_ARTS
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_BUSINESS
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_ENTREPRENUERS
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_KEYWORD
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_POLITICS
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_SPORTS
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_TRAVEL
import com.bintina.mynews.util.Constants.NOTIFICATION_TITLE
import com.bintina.mynews.util.MyApp.Companion.FILE_NAME
import com.bintina.mynews.util.MyApp.Companion.newsSharedPref
import com.bintina.mynews.util.MyApp.Companion.notificationEndDate
import com.google.gson.Gson
import java.util.Calendar
import java.util.concurrent.TimeUnit


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