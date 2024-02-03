package com.bintina.mynews

import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.bintina.mynews.notifications.controller.NotificationsActivity
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NotificationDisplayInstrumentedTest {
    @Rule
    @JvmField
    val rule: ActivityScenarioRule<NotificationsActivity> = ActivityScenarioRule(
        NotificationsActivity::class.java
    )

    @Test
    fun notification_display_screen_views_exist() {
        Assert.assertNotNull(ViewMatchers.withId(R.id.notification_display_fragment_container))
        Assert.assertNotNull(ViewMatchers.withId(R.id.notification_display_recycler_view_container))
    }
}