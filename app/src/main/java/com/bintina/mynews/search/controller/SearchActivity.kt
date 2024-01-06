package com.bintina.mynews.search.controller

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.bintina.mynews.R
import com.bintina.mynews.common.util.MyApp
import com.bintina.mynews.common.util.openAboutActivity
import com.bintina.mynews.common.util.openHelpActivity
import com.bintina.mynews.common.util.openNotificationsActivity
import com.bintina.mynews.common.util.openSearchActivity
import com.bintina.mynews.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity(), OnSearchClicked {
    lateinit var binding: ActivitySearchBinding

    companion object {
        const val KEY_SEARCH_FRAGMENT = "KEY_SEARCH_FRAGMENT"
        const val KEY_SEARCH_FRAGMENT_RESULTS = "KEY_SEARCH_FRAGMENT_RESULTS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val searchBtn = findViewById<View>(R.id.search_btn)

        searchBtn.setOnClickListener {
            openSearchActivity()
        }

        setSupportActionBar(findViewById(R.id.my_toolbar))
        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        toolbar.setBackgroundColor(
            ContextCompat.getColor(
                this,
                com.google.android.material.R.color.design_default_color_secondary
            )
        )

        val searchFragment = SearchFragment()
        searchFragment.listener = this

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.search_fragment_container, searchFragment, KEY_SEARCH_FRAGMENT)
        transaction.commit()


    }


    override fun onSearchClick() {
        val searchResultsFragment = SearchResultsFragment()
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(
            R.id.search_fragment_container,
            searchResultsFragment,
            KEY_SEARCH_FRAGMENT_RESULTS
        )
        transaction.commit()
        Log.d("onSearchClickLog", "transaction commited")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
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

            else -> return super.onOptionsItemSelected(item)

        }
    }
}
