package com.bintina.mynews

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.bintina.mynews.search.controller.SearchActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SearchActivityUnitTest {

    @Rule
    @JvmField
    val rule: ActivityScenarioRule<SearchActivity> =
        ActivityScenarioRule(SearchActivity::class.java)


    @Test
    fun check_that_the_query_is_formatted_correctly() {
        TODO("check that urlQuerySanitizer is implemented correctly once applied")
    }

    @Test
    fun user_can_enter_start_date() {
        //onView(withId(R.id.start_date_et)).perform()//find DatePicker action name
        //Check search query start date matches ui entry
    }

    @Test
    fun user_can_enter_end_date() {
        //onView(withId(R.id.start_date_et)).perform()//find DatePicker action name
        TODO("Check search query end date matches ui entry")
    }

    @Test
    fun user_can_select_check_box_works() {
        //onView(withId(R.id.checkbox_arts))
        //if clicked the box is checked assertTrue(SearchFragment.arts).
        TODO("check if the filters checked are filtered")
    }

    @Test
    fun search_btn_leads_to_result_fragment() {
        TODO("Check that the search button opens the result fragment correctly")
    }
}