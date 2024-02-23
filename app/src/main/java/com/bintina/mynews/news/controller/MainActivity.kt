package com.bintina.mynews.news.controller


import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.test.core.app.ActivityScenario.launch
import com.bintina.mynews.R
import com.bintina.mynews.common.data.repository.DataSource.loadArtNews
import com.bintina.mynews.common.data.repository.DataSource.loadBusinessNews
import com.bintina.mynews.common.data.repository.DataSource.loadNewsLists
import com.bintina.mynews.common.data.repository.DataSource.loadPopularNews
import com.bintina.mynews.common.data.repository.DataSource.loadScienceNews
import com.bintina.mynews.common.data.repository.DataSource.loadTopNews
import com.bintina.mynews.common.model.news.News
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
import com.bintina.mynews.news.controller.PagerAdapter
import com.bintina.mynews.news.view.adapter.Adapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * The main activity of the application
 */
class MainActivity : AppCompatActivity() {
    lateinit var adapter: Adapter

    //set view binding variable
    private lateinit var binding: ActivityMainBinding

    /**
     * Called when the activity is starting
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = Adapter()


        instantiateTodaysDate()

    /*    lifecycleScope.launch(Dispatchers.IO) {
            loadNewsLists(lifecycleScope)
            adapter.notifyDataSetChanged()
        }
            Log.d(
                "MainActLog",
                "TopStories has ${topStoriesList.size}, PopularStories has ${popularNewsList.size}, BusinessStories has ${businessStoriesList.size}, ArtStories has ${artStoriesList.size}, ScienceStories has ${scienceStoriesList.size}"
            )
*/
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
            openSearchActivity()
        }

        //Set up the ViewPager with TabLayout
        setupViewPager()
        //   progressIndicator.visibility = View.GONE

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

   /* private suspend fun getNewsLists():List<List<News?>> {
        lifecycleScope.launch { loadArtNews(lifecycleScope) }
        lifecycleScope.launch { loadScienceNews(lifecycleScope) }


        return listOf(
            popularNewsList,
            businessStoriesList, artStoriesList,
            scienceStoriesList)
    }*/
}