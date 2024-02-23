package com.bintina.mynews.search.controller

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bintina.mynews.R
import com.bintina.mynews.common.util.MyApp.Companion.searchKeyword
import com.bintina.mynews.common.util.goHome
import com.bintina.mynews.common.util.navigateToFragment
import com.bintina.mynews.common.util.navigateToSearchFragment
import com.bintina.mynews.common.util.openAboutActivity
import com.bintina.mynews.common.util.openHelpActivity
import com.bintina.mynews.common.util.openNotificationsActivity
import com.bintina.mynews.common.util.openSearchActivity
import com.bintina.mynews.databinding.ActivitySearchBinding
import com.bintina.mynews.search.SearchViewModel

/**
 * Activity for handling search functionality.
 */
class SearchActivity : AppCompatActivity(), OnSearchClicked {

    private lateinit var viewModel: SearchViewModel

    lateinit var binding: ActivitySearchBinding

    companion object {
        fun goToSearchResults() {
            val fragmentManager = supportFragmentManager
            val transaction = fragmentManager.beginTransaction()

            if (searchKeyword.isBlank()) {
                Toast.makeText(this, "Please enter a search term", Toast.LENGTH_LONG).show()
            } else {
                navigateToSearchFragment(transaction,R.id.search_fragment_container, KEY_SEARCH_FRAGMENT_RESULTS)
                Log.d("onSearchClickLog", "transaction commited")
            }
        }

        const val KEY_SEARCH_FRAGMENT = "KEY_SEARCH_FRAGMENT"
        const val KEY_SEARCH_FRAGMENT_RESULTS = "KEY_SEARCH_FRAGMENT_RESULTS"
    }

    /**
     * Called when the activity is first created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)


        // Set up the search button click listener
        val searchBtn = findViewById<View>(R.id.menu_search_btn)
        searchBtn.setOnClickListener {
            openSearchActivity()
        }

        // Set up the toolbar
        setSupportActionBar(findViewById(R.id.my_toolbar))
        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        toolbar.setBackgroundColor(
            ContextCompat.getColor(
                this,
                com.google.android.material.R.color.design_default_color_secondary
            )
        )

        // Create and initialize the initial search fragment
        val searchFragment = SearchFragment()
        searchFragment.listener = this

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.search_fragment_container, searchFragment, KEY_SEARCH_FRAGMENT)
        transaction.commit()
    }

    /**
     * Called when the search button is clicked.
     */
    override fun onSearchClick() {

        viewModel.search()
        goToSearchResults()
    }

     fun goToSearchResults() {

        // Replace the search fragment with the search results fragment


    }

    /**
     * Initialize the options menu.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    /**
     * Handle options menu item selection.
     */
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
