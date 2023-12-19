package com.bintina.mynews

import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.bintina.mynews.notifications.NotificationsActivity
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NotificationsInstrumentedTest {
    @Rule
    @JvmField
    val rule: ActivityScenarioRule<NotificationsActivity> = ActivityScenarioRule(NotificationsActivity::class.java)

    @Test
    fun fragment_container_exists(){
        assertNotNull(withId(R.id.overflow_fragment_container))
    }

    @Test
    fun notification_screen_views_exist(){
        assertNotNull(withId(R.id.notification_search_query_term_edit_text))
        assertNotNull(withId(R.id.notification_checkbox_arts))
        assertNotNull(withId(R.id.notification_checkbox_business))
        assertNotNull(withId(R.id.notification_checkbox_entreprenuers))
        assertNotNull(withId(R.id.notification_checkbox_politics))
        assertNotNull(withId(R.id.notification_checkbox_sports))
        assertNotNull(withId(R.id.notification_checkbox_travel))
        assertNotNull(withId(R.id.toggle_btn))
        assertNotNull(withId(R.id.toggle_text))
        

    }
}