package com.bintina.mynews


import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.bintina.mynews.MyApp.Companion.CURRENT_NEWS_STATE
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
//@LargeTest
class MainActivityUnitTest {
    @Rule
    @JvmField
    val rule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun asserts_current_news_state_is_valid_int() {
        assertTrue("CURRENT_NEWS_STATE in 0..4", CURRENT_NEWS_STATE in 0..4)
    }

    @Before
    fun position_and_current_news_state_at_starting_value(){
        assertTrue("CURRENT_NEWS_STATE = 0", CURRENT_NEWS_STATE == 0)
    }



    @Test
    fun second_tab_api_call_is_popular_news() {
        TODO(
            "checks api call matches" +
                    "preconditions" +
                    "CURRENT_NEWS_STATE = 1" +
                    //https://api.nytimes.com/svc/mostpopular/v2/viewed/1.json?api-key=zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf
                    "actions" +
                    "expections" +
                    "CURRENT_NEWS_STATE = 2" +
                    //https://api.nytimes.com/svc/topstories/v2/business.json?api-key=zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf
                    ""
        )
    }

    @Test
    fun third_tab_api_call_is_business_stories() {
        TODO(
            "checks api call matches" +
                    "preconditions" +
                    "CURRENT_NEWS_STATE = 2" +
                    //https://api.nytimes.com/svc/topstories/v2/business.json?api-key=zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf
                    "actions" +
                    "expections" +
                    "CURRENT_NEWS_STATE = 3" +
                    //https://api.nytimes.com/svc/topstories/v2/arts.json?api-key=zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf
                    ""
        )
    }

    @Test
    fun forth_tab_api_call_is_art_stories() {
        TODO(
            "checks api call matches" +
                    "preconditions" +
                    "CURRENT_NEWS_STATE = 3" +
                    //https://api.nytimes.com/svc/topstories/v2/arts.json?api-key=zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf
                    "actions" +
                    "expections" +
                    "CURRENT_NEWS_STATE = 4" +
                    //https://api.nytimes.com/svc/topstories/v2/science.json?api-key=zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf
                    //assert =
                    ""
        )
    }

    @Test
    fun fifth_tab_api_call_is_science_stories() {
        TODO(
            "checks api call matches" +
                    "preconditions" +
                    "CURRENT_NEWS_STATE = 4" +
                    "actions" +
                    "expections" +
                    //https://api.nytimes.com/svc/topstories/v2/science.json?api-key=zISBuhDClXAT0G1Lpmz0YnbaGfhPkGqf
                    ""
        )
    }

    @Test
    fun swiping_results_in_the_correct_screen() {
        TODO(
            "check that swiping results in the correct API calls" +
                    "                \"preconditions\" +\n" +
                    "                \"actions\" +\n" +
                    "                \"expections\")"
        )
    }


}