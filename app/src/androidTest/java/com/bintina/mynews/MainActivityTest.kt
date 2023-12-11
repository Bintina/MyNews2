package com.bintina.mynews

import androidx.test.espresso.Espresso.onView
import androidx.test.ext.junit.rules.ActivityScenarioRule
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
    fun search_button_navigates_to_search_activity(){
//        onView(withID(R.id.search_btn)).perform(Click())

        TODO("check that swiping results in the correct API calls")

        //Search Activity lifecycle = Active
    }

    /*
    Possible further tests:
    1. Database methods return correct List<class>
    2. Null values in the results are filtered
     */
}
