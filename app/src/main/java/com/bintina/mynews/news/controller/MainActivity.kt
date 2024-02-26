package com.bintina.mynews.news.controller


import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.bintina.mynews.R
import com.bintina.mynews.common.util.MyApp.Companion.artStoriesList
import com.bintina.mynews.common.util.MyApp.Companion.businessStoriesList
import com.bintina.mynews.common.util.MyApp.Companion.popularNewsList
import com.bintina.mynews.common.util.MyApp.Companion.scienceStoriesList
import com.bintina.mynews.common.util.MyApp.Companion.topStoriesList
import com.bintina.mynews.common.util.goHome
import com.bintina.mynews.common.util.instantiateTodaysDate
import com.bintina.mynews.common.util.openAboutActivity
import com.bintina.mynews.common.util.openHelpActivity
import com.bintina.mynews.common.util.openNotificationsActivity
import com.bintina.mynews.common.util.openSearchActivity
import com.bintina.mynews.databinding.ActivityMainBinding
import com.bintina.mynews.news.view.MainNewsFragment
import com.bintina.mynews.news.view.adapter.Adapter

/**
 * The main activity of the application
 */
class MainActivity : AppCompatActivity() {
    lateinit var adapter: Adapter

    lateinit var navController: NavController
    //set view binding variable
    private lateinit var binding: ActivityMainBinding

    companion object {
        // Key for the notification fragment
        const val KEY_MAIN_NEWS_FRAGMENT = "KEY_MAIN_NEWS_FRAGMENT"
    }

    /**
     * Called when the activity is starting
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(com.bintina.mynews.R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController



        instantiateTodaysDate()

            Log.d(
                "MainActLog",
                "TopStories has ${topStoriesList.size}, PopularStories has ${popularNewsList.size}, BusinessStories has ${businessStoriesList.size}, ArtStories has ${artStoriesList.size}, ScienceStories has ${scienceStoriesList.size}"
            )

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
        val searchBtn = findViewById<View>(R.id.menu_search_btn)
        searchBtn.setOnClickListener {
            val action = MainNewsFragmentDirections.actionNewsToSearch()
            navController.navigate(action)
                }

        // Create and initialize the initial search fragment
        val mainNewsFragment = MainNewsFragment()


        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.nav_host_fragment, mainNewsFragment,
            KEY_MAIN_NEWS_FRAGMENT
        )
        transaction.commit()

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