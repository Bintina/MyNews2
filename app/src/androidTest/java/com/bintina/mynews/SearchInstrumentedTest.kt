package com.bintina.mynews

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.bintina.mynews.common.util.instantiateTodaysDate
import com.bintina.mynews.search.controller.SearchActivity
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SearchInstrumentedTest {

    val sleepDuration: Long = 50000

    @Rule
    @JvmField
    val rule: ActivityScenarioRule<SearchActivity> = ActivityScenarioRule(
        SearchActivity::class.java
    )

    @Before
    fun instantiate_current_date() {
        instantiateTodaysDate()

    }

    @Test
    fun fragment_container_exists() {
        Assert.assertNotNull(ViewMatchers.withId(R.id.search_fragment_container))
    }

    @Test
    fun search_screen_views_function_as_expected() {
        Assert.assertNotNull(ViewMatchers.withId(R.id.search_query_term_edit_text))
        Espresso.onView(ViewMatchers.withId(R.id.search_query_term_edit_text))
            .perform(ViewActions.typeText("kenya"))
        Assert.assertNotNull(ViewMatchers.withId(R.id.checkbox_arts))
        Espresso.onView(ViewMatchers.withId(R.id.checkbox_arts))
            .perform(ViewActions.click())
        Assert.assertNotNull(ViewMatchers.withId(R.id.checkbox_business))
        Espresso.onView(ViewMatchers.withId(R.id.checkbox_business))
            .perform(ViewActions.click())
        Assert.assertNotNull(ViewMatchers.withId(R.id.checkbox_entreprenuers))
        Espresso.onView(ViewMatchers.withId(R.id.checkbox_entreprenuers))
            .perform(ViewActions.click())
        Assert.assertNotNull(ViewMatchers.withId(R.id.checkbox_politics))
        Espresso.onView(ViewMatchers.withId(R.id.checkbox_politics))
            .perform(ViewActions.click())
        Assert.assertNotNull(ViewMatchers.withId(R.id.checkbox_sports))
        Espresso.onView(ViewMatchers.withId(R.id.checkbox_sports))
            .perform(ViewActions.click())
        Assert.assertNotNull(ViewMatchers.withId(R.id.checkbox_travel))
        Espresso.onView(ViewMatchers.withId(R.id.checkbox_travel))
            .perform(ViewActions.click())
        Assert.assertNotNull(ViewMatchers.withId(R.id.menu_search_btn))
        Espresso.onView(ViewMatchers.withId(R.id.menu_search_btn)).perform(ViewActions.click())
        Assert.assertNotNull(ViewMatchers.withId(R.id.menu_search_btn))
        //assert Text matches Enable Notifications (once per day)

    }

    @Test
    fun start_search_button_is_clickable() {
        Assert.assertNotNull(ViewMatchers.withId(R.id.start_search_btn))
        Espresso.onView(ViewMatchers.withId(R.id.start_search_btn)).perform(ViewActions.click())

    }
}