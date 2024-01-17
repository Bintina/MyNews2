package com.bintina.mynews


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.bintina.mynews.common.util.goHome
import com.bintina.mynews.common.util.instantiateTodaysDate
import com.bintina.mynews.common.util.openAboutActivity
import com.bintina.mynews.common.util.openHelpActivity
import com.bintina.mynews.common.util.openNotificationsActivity
import com.bintina.mynews.common.util.openSearchActivity
import com.bintina.mynews.databinding.ActivityMainBinding
import com.bintina.mynews.news.controller.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

/**
 * The main activity of the application
 */
class MainActivity : AppCompatActivity() {

    //set view binding variable
    private lateinit var binding: ActivityMainBinding

    /**
     * Called when the activity is starting
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.myToolbar)
       //Customize the toolbar color
        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        toolbar.setBackgroundColor(
            ContextCompat.getColor(
                this,
                com.google.android.material.R.color.design_default_color_secondary
            )
        )

        //Set up click listener for search button
        val searchBtn = findViewById<View>(R.id.search_btn)
        searchBtn.setOnClickListener {
            openSearchActivity()
        }

        //Set up the ViewPager with TabLayout
        setupViewPager()

        instantiateTodaysDate()
    }

    //Set up ViewPager with TabLayoutMediator attached.
    private fun setupViewPager() {
        binding.pager.adapter = PagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
          //Set tab text based on position
            tab.text = when (position) {
                0 -> "Top Stories"
                1 -> "Popular"
                2 -> "Business"
                3 -> "Arts"
                4 -> "Science"
                else -> throw IllegalStateException("Unexpected position $position")
            }
            //Customize tab view background color to match toolbar
            tab.view.setBackgroundColor(getColor(com.google.android.material.R.color.design_default_color_secondary))


        }
            .attach()
    }

    //Initialize the contents of the Activity's standard options menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    //Called when menu item is selected.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.notifications_btn -> {
                openNotificationsActivity()
                return true
            }

            R.id.help_btn -> {
                openHelpActivity()
                return true
            }

            R.id.about_btn -> {
                openAboutActivity()
                return true
            }

            R.id.home_btn -> {
                goHome()
                return true
            }

            else -> return super.onOptionsItemSelected(item)

        }
    }


}