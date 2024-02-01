package com.bintina.mynews

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.bintina.mynews.notifications.controller.NotificationsActivity
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NotificationsInstrumentedTest {
    @Rule
    @JvmField
    val rule: ActivityScenarioRule<NotificationsActivity> = ActivityScenarioRule(
        NotificationsActivity::class.java)

    @Test
    fun fragment_container_exists(){
        assertNotNull(withId(R.id.notification_fragment_container))
    }

    @Test
    fun notification_screen_views_function_as_expected(){
        assertNotNull(withId(R.id.notification_search_query_term_edit_text))
        onView(withId(R.id.notification_search_query_term_edit_text)).perform(typeText("kenya"))
        assertNotNull(withId(R.id.notification_checkbox_arts))
        onView(withId(com.bintina.mynews.R.id.notification_checkbox_arts)).perform(click())
        assertNotNull(withId(R.id.notification_checkbox_business))
        onView(withId(com.bintina.mynews.R.id.notification_checkbox_business)).perform(click())
        assertNotNull(withId(R.id.notification_checkbox_entreprenuers))
        onView(withId(com.bintina.mynews.R.id.notification_checkbox_entreprenuers)).perform(click())
        assertNotNull(withId(R.id.notification_checkbox_politics))
        onView(withId(com.bintina.mynews.R.id.notification_checkbox_politics)).perform(click())
        assertNotNull(withId(R.id.notification_checkbox_sports))
        onView(withId(com.bintina.mynews.R.id.notification_checkbox_sports)).perform(click())
        assertNotNull(withId(R.id.notification_checkbox_travel))
        onView(withId(com.bintina.mynews.R.id.notification_checkbox_travel)).perform(click())
        assertNotNull(withId(R.id.toggle_btn))
        onView(withId(com.bintina.mynews.R.id.toggle_btn)).perform(click())
        assertNotNull(withId(R.id.toggle_text))
        //assert Text matches Enable Notifications (once per day)

    }
    @Test
    fun toggle_button_is_clickable(){
        assertNotNull(withId(R.id.toggle_btn))
        onView(withId(R.id.toggle_btn)).perform(click())
    }
}