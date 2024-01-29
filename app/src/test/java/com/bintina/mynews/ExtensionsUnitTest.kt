package com.bintina.mynews

import com.bintina.mynews.common.util.MyApp
import junit.framework.TestCase
import org.junit.Test

class ExtensionsUnitTest {

    @Test
    fun check_date_is_current() {
        TestCase.assertTrue(MyApp.defaultSearchEndDate == MyApp.currentDate.toString())
        TestCase.assertTrue(MyApp.defaultNotificationEndDate == MyApp.currentDate.toString())
    }
}