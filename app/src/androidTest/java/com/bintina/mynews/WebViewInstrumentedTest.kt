package com.bintina.mynews

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.bintina.mynews.news.controller.MainActivity
import com.bintina.mynews.news.view.WebViewActivity
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class WebViewInstrumentedTest {

    @Rule
    @JvmField
    val rule: ActivityScenarioRule<WebViewActivity> = ActivityScenarioRule(WebViewActivity::class.java)

    val sleepDuration: Long = 50000

    @Test
    fun news_item_click_opens_web_view(){

        Espresso.onView(ViewMatchers.withId(R.id.webview))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))

    }
}