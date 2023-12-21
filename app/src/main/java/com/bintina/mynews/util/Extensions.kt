package com.bintina.mynews.util

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.*
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.bintina.mynews.model.search.QueryDetails
import com.bintina.mynews.notifications.NotificationsReceiver
import com.bintina.mynews.util.MyApp.Companion.FILE_NAME
import com.bintina.mynews.util.MyApp.Companion.newsSharedPref
import com.google.gson.Gson


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

    return "news_desk(${trueFiltersList.joinToString(",")})"
}

public fun setAlarm(context: Context) {
    val alarmManager = context.getSystemService(ALARM_SERVICE) as? AlarmManager

    val notificationRequestCode = 1001
    val intent = Intent(context, NotificationsReceiver::class.java)
    intent.action = "FOO"


    val alarmStartDelay = 5L
    val alarmIntervalInMillis = 20_000L
    val alarmManagerTriggerTimeInMillis = System.currentTimeMillis() + alarmStartDelay * 1_000L
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        notificationRequestCode,
        intent,
        PendingIntent.FLAG_IMMUTABLE
    )

    alarmManager?.setRepeating(
        AlarmManager.RTC_WAKEUP,
        alarmManagerTriggerTimeInMillis,
        alarmIntervalInMillis,
        pendingIntent
    )

    Toast.makeText(context, "Notification broadcast sent", Toast.LENGTH_LONG).show()
}