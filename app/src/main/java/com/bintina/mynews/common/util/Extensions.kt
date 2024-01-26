package com.bintina.mynews.common.util

import android.content.Context
import android.content.Intent
import android.util.Log
import com.bintina.mynews.MainActivity
import com.bintina.mynews.common.model.news.News
import com.bintina.mynews.common.model.search.Doc
import com.bintina.mynews.common.util.MyApp.Companion.currentDate
import com.bintina.mynews.common.util.MyApp.Companion.notificationStartDate
import com.bintina.mynews.common.util.MyApp.Companion.searchEndDate
import com.bintina.mynews.common.util.MyApp.Companion.searchStartDate
import com.bintina.mynews.notifications.controller.NotificationsActivity
import com.bintina.mynews.overflow.view.AboutActivity
import com.bintina.mynews.overflow.view.HelpActivity
import com.bintina.mynews.search.controller.SearchActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


/**
 * Retrieves selected filters based on boolean values.
 *
 * @param artsBoolean Boolean value for arts filter.
 * @param businessBoolean Boolean value for business filter.
 * @param entreprenuersBoolean Boolean value for entrepreneurs filter.
 * @param politicsBoolean Boolean value for politics filter.
 * @param sportsBoolean Boolean value for sports filter.
 * @param travelBoolean Boolean value for travel filter.
 * @return A string containing selected filters or null if no filters are selected.
 */
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

/**
 * Initializes today's date without time.
 *
 * @return The current date without time.
 */

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
/**
 * Calculates and returns the default start date for notifications (6 months ago from the current date).
 *
 * @param currentDate The current date.
 * @return The default start date for notifications.
 */
fun getDefaultNotificationStartDate(currentDate: Date): Date {
    val calendar = Calendar.getInstance()
    calendar.time = currentDate
    calendar.add(Calendar.MONTH, -6)

    notificationStartDate = calendar.time
    return notificationStartDate
}
/**
 * Calculates and returns the default start date for searches (5 years ago from the current date).
 *
 * @param currentDate The current date.
 * @return The default start date for searches.
 */
fun getDefaultSearchStartDate(currentDate: Date): Date {
    val calendar = Calendar.getInstance()
    calendar.time = currentDate
    calendar.add(Calendar.YEAR, -5)

    searchStartDate = calendar.time
    return searchStartDate
}

/**
 * Converts a Date type to a formatted String.
 *
 * @param date The Date object to be converted.
 * @param format The desired format for the String.
 * @return The formatted String representation of the Date.
 */
fun getStringDates(date: Date, format: String): String {
    val dateFormat = SimpleDateFormat(format, Locale.US)

    val calendar = Calendar.getInstance()
    calendar.time = date
    calendar.add(Calendar.MONTH, 0)

    return dateFormat.format(calendar.time)
}
/**
 * Converts a Date type to a formatted String suitable for API requests.
 *
 * @param date The Date object to be converted.
 * @param format The desired format for the String.
 * @return The formatted String representation of the Date for API requests.
 */
fun getApiDates(date: Date, format: String): String {
    val dateFormat = SimpleDateFormat(format, Locale.US)
    return dateFormat.format(date)
}

//AppBar Navigation Methods.........................................................................
/**
 * Opens the SearchActivity.
 */
fun Context.openSearchActivity(){
    val intent = Intent(this, SearchActivity::class.java)
    startActivity(intent)
    Log.d("MagnifyingglassButtonClick", "Magnifying button click method reached end")
}
/**
 * Opens the NotificationsActivity.
 */
fun Context.openNotificationsActivity(){
    val intent = Intent(this, NotificationsActivity::class.java)
    startActivity(intent)
    Log.d("NavigateToNotificationAct", "Notifications button clicked")
}
/**
 * Opens the HelpActivity.
 */
fun Context.openHelpActivity(){
    val intent = Intent(this, HelpActivity::class.java)
    startActivity(intent)
}/**
 * Opens the AboutActivity.
 */
fun Context.openAboutActivity(){
    val intent = Intent(this, AboutActivity::class.java)
    startActivity(intent)
}
/**
 * Navigates to the MainActivity.
 */
fun Context.goHome(){
    val intent = Intent(this, MainActivity::class.java)
    startActivity(intent)
}

/**
 * Filters News Results.
 */
//filter DataSource results.........................................................................
fun filterNewsResult(result: List<News?>?): List<News?> {
    return result?.filter { news ->
        // Filter condition: abstract, section, and subsection should not be blank
        news?.abstract?.isNotBlank() == true &&
                news.section?.isNotBlank() == true &&
                news.subsection?.isNotBlank() == true
    } ?: emptyList()

}
/**
 * Filters Doc Results.
 */
fun filterSearchResult(result: List<Doc?>?): List<Doc?> {
    return result?.filter { doc ->
        // Filter condition: abstract, section, and subsection should not be blank
        doc?.abstract?.isNotBlank() == true &&
                doc.sectionName?.isNotBlank() == true &&
                doc.subsectionName?.isNotBlank() == true
    } ?: emptyList()

}