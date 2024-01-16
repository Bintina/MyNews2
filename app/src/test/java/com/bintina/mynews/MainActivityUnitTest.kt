package com.bintina.mynews
import android.content.Context
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.bintina.mynews.common.util.*
import com.bintina.mynews.databinding.ActivityMainBinding
import com.bintina.mynews.news.controller.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockedConstruction
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MainActivityUnitTest {

    @Mock
    lateinit var mockContext: Context

    @Mock
    private lateinit var mockToolbar: Toolbar

    @Mock
    private lateinit var mockBinding: ActivityMainBinding

    @Mock
    private lateinit var mockPagerAdapter: PagerAdapter

    @Mock
    private lateinit var mockTabLayoutMediator: TabLayoutMediator

    private lateinit var mainActivity: MainActivity

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mainActivity = MainActivity()
        mainActivity.binding = mockBinding
        mainActivity.setupViewPager()
    }

    @Test
    fun testToolbarColor() {
        mainActivity.onCreate(null)
        verify(mockBinding.myToolbar).setBackgroundColor(
            ContextCompat.getColor(
                mainActivity,
                com.google.android.material.R.color.design_default_color_secondary
            )
        )
    }


    @Test
    fun testSearchButtonClickListener() {
        mainActivity.onCreate(null)
        mainActivity.findViewById<View>(R.id.search_btn).performClick()
        Espresso.onView(ViewMatchers.withId(R.id.search_fragment_container))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testViewPagerSetup() {
        mainActivity.onCreate(null)
        verify(mockBinding.pager).adapter = mockPagerAdapter
        verify(mockTabLayoutMediator).attach()
    }

    @Test
    fun testOptionsMenu() {
        val mockMenu = mock(Menu::class.java)
        mainActivity.onCreateOptionsMenu(mockMenu)
        verify(mainActivity.menuInflater).inflate(R.menu.menu, mockMenu)
    }

    @Test
    fun testNotificationsMenuItem() {
        val mockMenuItem = mock(MenuItem::class.java)
        mainActivity.onOptionsItemSelected(mockMenuItem)
        Espresso.onView(ViewMatchers.withId(R.id.notification_fragment_container))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    @Test
    fun testHelpMenuItem() {
        val mockMenuItem = mock(MenuItem::class.java)
        mainActivity.onOptionsItemSelected(mockMenuItem)
        Espresso.onView(ViewMatchers.withId(R.id.help_content))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testAboutMenuItem() {
        val mockMenuItem = mock(MenuItem::class.java)
        mainActivity.onOptionsItemSelected(mockMenuItem)
        Espresso.onView(ViewMatchers.withId(R.id.about_content))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testHomeMenuItem() {
        val mockMenuItem = mock(MenuItem::class.java)
        mainActivity.onOptionsItemSelected(mockMenuItem)
        Espresso.onView(ViewMatchers.withId(R.id.activity_main_scroll_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}