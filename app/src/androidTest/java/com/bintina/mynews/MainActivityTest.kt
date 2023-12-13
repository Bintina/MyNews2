package com.bintina.mynews

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.intent.rule.IntentsRule
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.bintina.mynews.news.adapter.Adapter
import com.bintina.mynews.util.MyApp.Companion.newsInPossitionUrl
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val rule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)


    var mainActivity: MainActivity? = null

    /*    @Before
        fun main_activity_is_loaded(){
            mainActivity = MainActivity()!!
        }*/
    @Test
    fun search_button_navigates_to_search_fragment() {
        assertNotNull(withId(R.id.search_btn))
        onView(withId(R.id.search_btn)).perform(click())
        onView(withId(R.id.search_query_fragment_container)).check(matches(isEnabled()))

    }


    /*    @Test
        fun testLaunchSearchActivityOnButtonActionClicked() {
            assertNotNull(mMainActivity.findViewById(R.id.action_search))
            onView(withId(R.id.action_search)).perform(click())

            //timeOut in milliseconds
            val searchActivity: Activity =
                getInstrumentation().waitForMonitorWithTimeout(mMonitor, 5000)
            assertNotNull(searchActivity)
        }*/
    @get:Rule
    val intentsRule = IntentsRule()

    @Before
    fun before() {
        Intents.init()
    }

    @Test
    fun item_view_click_navigates_to_news_url() {

        //recycler view references
        val recyclerViewId = R.id.recyclerview

        //positions to click
       /* val startPosition = 0
        val endPosition = 10*/
        val simplePosition = 0

        //iteration intent init
        Intents.init()

        // Perform a click action on the item at the specified position
       // for (position in startPosition..endPosition) {

            onView(withId(recyclerViewId)).perform(
                RecyclerViewActions.actionOnItemAtPosition<Adapter.ItemViewHolder>(
                    simplePosition,
                    click()
                )
            )

            assertNotNull(withId(R.id.card_contents))

            //Define expected url
            val url = newsInPossitionUrl

            // Define the expected intent for opening a browser
            val expectedIntent = org.hamcrest.CoreMatchers.allOf(
                hasAction(Intent.ACTION_VIEW),
                hasData(url)
            )

            // Check that the expected intent was sent
            Intents.intended(expectedIntent)


        //}
/*
        //release iteration init
        Intents.release()
*/

    }

    @After
    fun after() {
        Intents.release()
    }


    @Test
    fun check_tab_titles_correct() {
        /*  TODO("Try using esspresso methods:" +
          "onView(withId(R.id.text_simple)).check(matches(withText("")))" +
                  "")*/
    }
}


/*
Possible further tests:
1. Database methods return correct List<class>
2. Null values in the results are filtered
 */

