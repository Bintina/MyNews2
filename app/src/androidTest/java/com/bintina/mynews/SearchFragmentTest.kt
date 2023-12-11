package com.bintina.mynews

import android.app.Activity
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bintina.mynews.search.controller.SearchActivity
import com.bintina.mynews.search.controller.SearchFragment
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchFragmentTest {

    @Rule
    @JvmField
    val rule: ActivityScenarioRule<SearchActivity> = ActivityScenarioRule(SearchActivity::class.java)
    @Test
    fun user_can_enter_search_term(){
        Espresso.onView(ViewMatchers.withId(R.id.search_query_term_edit_text))
            .perform(ViewActions.typeText("children"))
    }
}