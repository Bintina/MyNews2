package com.bintina.mynews

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.intent.rule.IntentsRule
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.lifecycle.ActivityLifecycleMonitor
import com.bintina.mynews.news.adapter.Adapter
import com.bintina.mynews.util.MyApp
import com.bintina.mynews.util.MyApp.Companion.CURRENT_NEWS_STATE
import com.bintina.mynews.util.MyApp.Companion.newsInPossitionUrl
import junit.framework.Assert.assertTrue
import junit.framework.TestCase
import junit.framework.TestCase.assertNotNull
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
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

    @Test
    fun search_button_navigates_to_search_fragment() {
        assertNotNull(withId(R.id.search_btn))
        onView(withId(R.id.search_btn)).perform(click())
        // Wait for a moment to let the fragment container load
        Thread.sleep(2000) // You can adjust the delay based on your application's loading time

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
  /*  @get:Rule
    val intentsRule = IntentsRule()*/



    @Test
    fun recycler_view_exists() {

        // Check if RecyclerView is displayed
        onView(withId(R.id.recyclerview)).check(matches(isDisplayed()))

     /*   // Perform a click action on the first item's card_contents
        onView(withId(R.id.recyclerview))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<Adapter.ItemViewHolder>(
                    0,
                    object : ViewAction {
                        override fun getDescription(): String = "Click on card_contents"

                        override fun getConstraints(): Matcher<View> =
                            allOf(isAssignableFrom(RecyclerView::class.java), isDisplayed())

                        override fun perform(uiController: UiController?, view: View?) {
                            val recyclerView = view as RecyclerView
                            val viewHolder =
                                recyclerView.findViewHolderForAdapterPosition(0) as Adapter.ItemViewHolder
                            val cardContentsView =
                                viewHolder.itemView.findViewById<View>(R.id.card_contents)
                            cardContentsView.performClick()
                        }
                    })
            )
*/
    }
    @Before
    fun position_and_current_news_state_at_starting_value(){
        TestCase.assertTrue("CURRENT_NEWS_STATE = 0", MyApp.CURRENT_NEWS_STATE == 0)
    }
    @Test
    fun swipes_first_left_successfully() {
        //first Swipe
        onView(withId(R.id.pager)).perform(ViewActions.swipeLeft())
         assertTrue(CURRENT_NEWS_STATE == 1)
    }
/*
 I am curious about why this test doesn't work for the second swipe.

 @Before
    fun position_and_current_news_state_at_second_value(){
        TestCase.assertTrue("CURRENT_NEWS_STATE = 1", MyApp.CURRENT_NEWS_STATE == 1)
    }
    @Test
    fun swipes_second_left_successfully() {

        //second Swipe
        onView(withId(R.id.pager)).perform(ViewActions.swipeLeft())
         assertTrue(CURRENT_NEWS_STATE == 2)
    }*/

        /* //third Swipe
        onView(withId(R.id.pager)).perform(ViewActions.swipeLeft())
         assertTrue(CURRENT_NEWS_STATE == 3)
        //fourth Swipe
        onView(withId(R.id.pager)).perform(ViewActions.swipeLeft())
         assertTrue(CURRENT_NEWS_STATE == 4)
        //fifth Swipe
        onView(withId(R.id.pager)).perform(ViewActions.swipeLeft())
         assertTrue(CURRENT_NEWS_STATE == 4)
        //sixth Swipe
        onView(withId(R.id.pager)).perform(ViewActions.swipeLeft())
         assertTrue(CURRENT_NEWS_STATE == 4)
*/



}


/*
Possible further tests:
1. Database methods return correct List<class>
2. Null values in the results are filtered
 */

