package com.bintina.mynews

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bintina.mynews.search.controller.SearchActivity
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchActivityTest {

    @Rule
    @JvmField
    val rule: ActivityScenarioRule<SearchActivity> = ActivityScenarioRule(SearchActivity::class.java)

    @Test
    fun user_can_enter_search_term(){
        //check if exists or is displayed
        assertNotNull(withId(R.id.search_query_term_edit_text))
        onView(withId(R.id.search_query_term_edit_text)).perform(typeText("children"))
        //Check query value matches entered text
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


}