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


fun showStartDatePicker(context: Context, view: TextView,): String{
    val currentDate = Calendar.getInstance()

    currentDate.add(Calendar.YEAR, -5)

    val initialStartYear = currentDate.get(Calendar.YEAR)
    val initialStartMonth = currentDate.get(Calendar.MONTH)
    val initialStartDay = currentDate.get(Calendar.DAY_OF_MONTH)

    var selectedDate = String.format("%d-%02d-%02d", initialStartYear, initialStartMonth + 1, initialStartDay)

    val datePickerDialog = DatePickerDialog(
        context,
        { view, year, month, dayOfMonth ->
            // Handle the selected date
            selectedDate = String.format("%d-%02d-%02d", year, month + 1, dayOfMonth)
            // Update the TextView or perform any other action
        },
        // Set initial date in the date picker
                initialStartYear, initialStartMonth, initialStartDay
    )

    // Show the date picker dialog
    datePickerDialog.show()

    return selectedDate
}
fun showEndDatePicker(context: Context, view: View,): String{
    val currentDate = Calendar.getInstance()
    val initialEndYear = currentDate.get(Calendar.YEAR)
    val initialEndMonth = currentDate.get(Calendar.MONTH)
    val initialEndDay = currentDate.get(Calendar.DAY_OF_MONTH)

    var selectedDate = String.format("%d-%02d-%02d", initialEndYear, initialEndMonth + 1, initialEndDay)

    val datePickerDialog = DatePickerDialog(
        context,
        { view, year, month, dayOfMonth ->
            // Handle the selected date
            selectedDate = String.format("%d-%02d-%02d", year, month + 1, dayOfMonth)
            // Update the TextView or perform any other action

        },
        // Set initial date in the date picker
                initialEndYear, initialEndMonth, initialEndDay
    )

    // Show the date picker dialog
    datePickerDialog.show()

    return selectedDate
}

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