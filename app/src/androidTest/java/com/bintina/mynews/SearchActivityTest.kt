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
        //Check query value matches entered text
    }

    @Test
    fun check_that_the_query_is_formatted_correctly(){
        TODO("check that urlQuerySanitizer is implemented correctly once applied")
    }

    @Test
    fun user_can_enter_start_date(){
        onView(withId(R.id.start_date_et)).perform()//find DatePicker action name
        //Check search query start date matches ui entry
    }
    @Test
    fun user_can_enter_end_date(){
        onView(withId(R.id.start_date_et)).perform()//find DatePicker action name
        TODO("Check search query end date matches ui entry")
    }

    @Test
    fun user_can_select_check_box_and_reflects_in_api_call(){
        TODO("check if the filters checked are filtered")
    }

    @Test
    fun search_btn_leads_to_result_fragment(){
        TODO("Check that the search button opens the result fragment correctly")
    }
}