package com.bintina.mynews

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bintina.mynews.search.controller.SearchActivity
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
        onView(withId(R.id.search_query_term_edit_text)).perform(typeText("children"))
    }

    @Test
    fun user_can_enter_start_date(){
        onView(withId(R.id.start_date_et)).perform()
    }
}