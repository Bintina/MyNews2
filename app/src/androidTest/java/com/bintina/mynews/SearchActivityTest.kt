package com.bintina.mynews

import android.widget.DatePicker
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bintina.mynews.search.controller.SearchActivity
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Calendar

@RunWith(AndroidJUnit4::class)
class SearchActivityTest {

    @Rule
    @JvmField
    val rule: ActivityScenarioRule<SearchActivity> = ActivityScenarioRule(SearchActivity::class.java)

    @Test
    fun user_can_enter_search_term(){
        assertNotNull(withId(R.id.search_query_term_edit_text))
        onView(withId(R.id.search_query_term_edit_text)).perform(typeText("children"))
    }
    @Test
    fun check_checkboxes(){
        assertNotNull(withId(R.id.checkbox_arts))
        onView(withId(R.id.checkbox_arts)).perform(click())
        assertNotNull(withId(R.id.checkbox_business))
        onView(withId(R.id.checkbox_business)).perform(click())
        assertNotNull(withId(R.id.checkbox_entreprenuers))
        onView(withId(R.id.checkbox_entreprenuers)).perform(click())
        assertNotNull(withId(R.id.checkbox_politics))
        onView(withId(R.id.checkbox_politics)).perform(click())
        assertNotNull(withId(R.id.checkbox_sports))
        onView(withId(R.id.checkbox_sports)).perform(click())
        assertNotNull(withId(R.id.checkbox_travel))
        onView(withId(R.id.checkbox_travel)).perform(click())
    }
    @Test
    fun pick_date_in_start_date_date_picker_dialog() {
        // Click on the button or view that opens the DatePickerDialog
        onView(withId(R.id.start_date_et)).perform(click())

        // Get the current date from the system
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val currentMonth = Calendar.getInstance().get(Calendar.MONTH)

        // Pick a date in the future (you can customize this based on your requirements)
        val pastYear = currentYear - 1
        val pastMonth = currentMonth -1
        val pastDay = 10

        // Open the DatePickerDialog
        onView(isAssignableFrom(DatePicker::class.java)).perform(PickerActions.setDate(pastYear, pastMonth, pastDay))

        // Click on the "OK" button or perform whatever action is required to confirm the date selection
        onView(withId(android.R.id.button1)).perform(click())

        // Check if the date has been updated in your UI (you may need to customize this based on your UI)
        onView(withId(R.id.start_date_et))
            .check(matches(withText("$pastYear-$pastMonth-$pastDay"))) // Adjust this according to your UI
    }
    @Test
    fun pick_date_in_end_date_date_picker_dialog() {
        // Click on the button or view that opens the DatePickerDialog
        onView(withId(R.id.end_date_et)).perform(click())

        // Get the current date from the system
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val currentMonth = Calendar.getInstance().get(Calendar.MONTH)

        // Pick a date in the future (you can customize this based on your requirements)
        val pastYear = currentYear - 2
        val pastMonth = currentMonth -1
        val pastDay = 10

        // Open the DatePickerDialog
        onView(isAssignableFrom(DatePicker::class.java)).perform(PickerActions.setDate(pastYear, pastMonth, pastDay))

        // Click on the "OK" button or perform whatever action is required to confirm the date selection
        onView(withId(android.R.id.button1)).perform(click())

        // Check if the date has been updated in your UI (you may need to customize this based on your UI)
        onView(withId(R.id.end_date_et))
            .check(matches(withText("$pastYear-$pastMonth-$pastDay"))) // Adjust this according to your UI
    }
}