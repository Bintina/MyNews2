package com.bintina.mynews


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.bintina.mynews.common.util.MyApp.Companion.currentDate
import com.bintina.mynews.common.util.MyApp.Companion.defaultNotificationEndDate
import com.bintina.mynews.common.util.MyApp.Companion.defaultSearchEndDate
import junit.framework.TestCase.assertTrue
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

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testViewPagerSetup() {
        // Verify that the PagerAdapter is set
        onView(withId(R.id.pager)).check(matches(isDisplayed()))

        // Verify that the tab text is set correctly
        onView(withText("Top Stories")).check(matches(isDisplayed()))
        onView(withText("Popular")).check(matches(isDisplayed()))
        onView(withText("Business")).check(matches(isDisplayed()))
        onView(withText("Arts")).check(matches(isDisplayed()))
        onView(withText("Science")).check(matches(isDisplayed()))
    }

    @Test
    fun check_date_is_current(){
        assertTrue(defaultSearchEndDate == currentDate.toString())
        assertTrue(defaultNotificationEndDate == currentDate.toString())
    }
}