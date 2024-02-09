package com.bintina.mynews

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.bintina.mynews.news.controller.MainActivity
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainActivityInstrumentedTest {

    @Rule
    @JvmField
    val rule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    val sleepDuration: Long = 50000

    @Test
    fun recycler_view_exists() {
        // Check if RecyclerView is displayed
        onView(withId(R.id.recyclerview)).check(matches(isDisplayed()))

    }

    @Test
    fun search_button_navigates_to_search_fragment() {
        assertNotNull(withId(R.id.menu_search_btn))
        onView(withId(R.id.menu_search_btn)).perform(click())
        Thread.sleep(sleepDuration) // You can adjust the delay based on your application's loading time

        onView(withId(R.id.search_query_fragment_container)).check(matches(isEnabled()))
    }

    @Test
    fun options_menu_buttons_exist() {
        assertNotNull(withId(R.id.menu_layout))
        assertNotNull(withId(R.id.notifications_btn))
        assertNotNull(withId(R.id.about_btn))
        assertNotNull(withId(R.id.help_btn))

    }

    @Test
    fun options_menu_notification_btn_inflates_notification_fragment() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext())
        onView(withText(R.string.notifications)).perform(click())
        Thread.sleep(sleepDuration) // You can adjust the delay based on your application's loading time

        onView(withId(R.id.notifications_settings_container)).check(matches(isEnabled()))
    }

    @Test
    fun options_menu_help_btn_is_clickable() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext())
        onView(withText(R.string.help)).perform(click())
        Thread.sleep(sleepDuration) // You can adjust the delay based on your application's loading time

        onView(withId(R.id.help_title)).check(matches(isEnabled()))
    }

    @Test
    fun options_menu_about_btn_is_clickable() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext())
        onView(withText(R.string.about)).perform(click())
        Thread.sleep(sleepDuration)

        onView(withId(R.id.about_title)).check(matches(isEnabled()))
    }

    @Test
    fun news_item_click_opens_web_view(){
        assertNotNull(withId(R.id.card_contents))
        onView(withId(R.id.card_contents)).perform(click())
        Thread.sleep(sleepDuration)

        onView(withId(R.id.webview)).check(matches(isEnabled()))

    }
}