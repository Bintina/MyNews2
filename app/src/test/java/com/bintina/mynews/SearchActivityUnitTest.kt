package com.bintina.mynews

import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.bintina.mynews.search.controller.SearchActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.Assert.assertNotNull
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled

import androidx.test.espresso.matcher.ViewMatchers.withText

import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.bintina.mynews.common.util.MyApp
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before


@RunWith(JUnit4::class)
class SearchActivityUnitTest {

    val sleepDuration: Long = 50000

    @Rule
    @JvmField
    val rule: ActivityScenarioRule<SearchActivity> = ActivityScenarioRule(SearchActivity::class.java)

    @Before
    fun open_search_activity(){
        assertNotNull(withId(R.id.search_fragment_container))
    }
    @Test
    fun search_keyword_is_search_term_is_entered(){
        onView(withId(R.id.search_query_term_edit_text)).perform(ViewActions.typeText("kenya"))
        assertNotNull(withId(R.id.start_search_btn))
        onView(withId(R.id.start_search_btn)).perform(click())
        Thread.sleep(sleepDuration)

        assertEquals(MyApp.searchKeyword, "kenya")

    }

}