package com.bintina.mynews

import android.app.ActionBar
import android.app.ActionBar.NAVIGATION_MODE_STANDARD
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.bintina.mynews.business.controller.BusinessNewsFragment
import com.bintina.mynews.databinding.ActivityMainBinding
import com.bintina.mynews.popular.controller.PopularNewsFragment
import com.bintina.mynews.topstories.controller.TopStoriesFragment
import com.google.android.material.navigation.NavigationBarMenu

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<PopularNewsFragment>(R.id.fragment_container_view)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.top_stories_btn -> {
                navigateToTopStories()
                true
            }

            R.id.popular_btn -> {
                navigateToPopularNews()
                true
            }

            R.id.business_btn -> {
                navigateToBusiness()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToBusiness() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<BusinessNewsFragment>(R.id.fragment_container_view)
        }
    }

    private fun navigateToPopularNews() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<PopularNewsFragment>(R.id.fragment_container_view)
        }
    }

    private fun navigateToTopStories() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<TopStoriesFragment>(R.id.fragment_container_view)
        }
    }
}