package com.bintina.mynews.common.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.bintina.mynews.MainActivity
import com.bintina.mynews.common.model.search.QueryDetails
import com.bintina.mynews.common.util.MyApp.Companion.FILE_NAME
import com.bintina.mynews.common.util.MyApp.Companion.currentDate
import com.bintina.mynews.common.util.MyApp.Companion.newsSharedPref

import com.bintina.mynews.common.util.MyApp.Companion.notificationStartDate
import com.bintina.mynews.common.util.MyApp.Companion.searchEndDate
import com.bintina.mynews.common.util.MyApp.Companion.searchStartDate
import com.bintina.mynews.notifications.controller.NotificationsActivity
import com.bintina.mynews.overflow.view.AboutActivity
import com.bintina.mynews.overflow.view.HelpActivity
import com.bintina.mynews.search.controller.SearchActivity
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


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

fun instantiateTodaysDate(): Date {
    val currentDateWithoutTime = Calendar.getInstance().apply {
        time = Date()
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.time

    currentDate = currentDateWithoutTime
    searchEndDate = currentDate


    return currentDate

}

fun getDefaultNotificationStartDate(currentDate: Date): Date {
    val calendar = Calendar.getInstance()
    calendar.time = currentDate
    calendar.add(Calendar.MONTH, -6)

    notificationStartDate = calendar.time
    return notificationStartDate
}

fun getDefaultSearchStartDate(currentDate: Date): Date {
    val calendar = Calendar.getInstance()
    calendar.time = currentDate
    calendar.add(Calendar.YEAR, -5)

    searchStartDate = calendar.time
    return searchStartDate
}

//Date type to String type conversion.
fun getStringDates(date: Date, format: String): String {
    val dateFormat = SimpleDateFormat(format, Locale.US)

    val calendar = Calendar.getInstance()
    calendar.time = date
    calendar.add(Calendar.MONTH, 0) // Adding 1 to the month

    return dateFormat.format(calendar.time)
}
fun getApiDates(date: Date, format: String): String {
    val dateFormat = SimpleDateFormat(format, Locale.US)
    return dateFormat.format(date)
}

//AppBar Methods
fun Context.openSearchActivity(){
    val intent = Intent(this, SearchActivity::class.java)
    startActivity(intent)
    Log.d("MagnifyingglassButtonClick", "Magnifying button click method reached end")
}
fun Context.openNotificationsActivity(){
    val intent = Intent(this, NotificationsActivity::class.java)
    startActivity(intent)
    Log.d("NavigateToNotificationAct", "Notifications button clicked")
}
fun Context.openHelpActivity(){
    val intent = Intent(this, HelpActivity::class.java)
    startActivity(intent)
}
fun Context.openAboutActivity(){
    val intent = Intent(this, AboutActivity::class.java)
    startActivity(intent)
}
fun Context.goHome(){
    val intent = Intent(this, MainActivity::class.java)
    startActivity(intent)
}