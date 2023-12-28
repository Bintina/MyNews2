package com.bintina.mynews

import android.content.Context
import com.bintina.mynews.search.controller.SearchResultsFragment
import com.bintina.mynews.common.util.MyApp.Companion.FILE_NAME
import com.bintina.mynews.common.util.MyApp.Companion.newsFragmentString
import com.bintina.mynews.common.util.MyApp.Companion.newsJson
import com.bintina.mynews.common.util.MyApp.Companion.newsSharedPref
import com.google.gson.Gson

class TempSpace {


    /*  val bundle = arguments
           Log.d("bundleNDFLog", "bundle = $bundle")
           bundle?.let {
               val notificationKeyword = it.getString(NOTIFICATION_KEY_KEYWORD)
               val arts = it.getBoolean(NOTIFICATION_KEY_ARTS)
               val business = it.getBoolean(NOTIFICATION_KEY_BUSINESS)
               val entreprenuers = it.getBoolean(NOTIFICATION_KEY_ENTREPRENUERS)
               val politics = it.getBoolean(NOTIFICATION_KEY_POLITICS)
               val sports = it.getBoolean(NOTIFICATION_KEY_SPORTS)
               val travel = it.getBoolean(NOTIFICATION_KEY_TRAVEL)
   */
/*    val resultFragment = SearchResultsFragment()
    val transaction = fragmentManager?.beginTransaction()
    transaction?.replace(R.id.search_fragment_container, resultFragment)?.commit()*/

/*// withId(R.id.my_view) is a ViewMatcher
// click() is a ViewAction
// matches(isDisplayed()) is a ViewAssertion
    onView(withId(R.id.my_view))
    .perform(click())
    .check(matches(isDisplayed()))*/
/*//SharedPreference Methods
//------>>Pref......................................................................................
fun objectToPreference(context: Context, mood: Mood, PREFERENCE_NAME: String) {

    moodJsonString = objectToJson(context, mood)

    jsonToPreference(context, moodJsonString, PREFERENCE_NAME)
}

    fun objectToJson(context: Context, mood: Mood): String {
        val moodJsonString = Gson().toJson(mood)

        return moodJsonString
    }

    fun jsonToPreference(context: Context, moodJsonString: String, PREFERENCE_NAME: String) {
        moodSharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val moodSharedPrefEditor = moodSharedPref.edit()

        moodSharedPrefEditor.putString(PREFERENCE_NAME, moodJsonString).apply()
    }

    //------>>Object....................................................................................
    fun preferenceToObject(context: Context,PREFERENCE_NAME: String):Mood {
        moodJsonString = preferenceToJson(context,PREFERENCE_NAME)
        dayMoodObject = jsonToObject(context, moodJsonString)

        return dayMoodObject
    }

    fun preferenceToJson(context: Context, PREFERENCE_NAME: String): String {
        moodSharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

        moodJsonString = moodSharedPref.getString(PREFERENCE_NAME, "").toString()

        val defaultMoodString = objectToJson(context, defaultMoodObject)

        if (moodJsonString.isNullOrEmpty()) {
            return defaultMoodString
        } else {
            return moodJsonString
        }
    }

    fun jsonToObject(context: Context,moodJsonString: String): Mood {

        dayMoodObject = Gson().fromJson<Mood>(moodJsonString, Mood::class.java)

        return dayMoodObject

    }*/
}
//rerouting Science to top xml (recycler view)
//CRASH

/*
something
*/
