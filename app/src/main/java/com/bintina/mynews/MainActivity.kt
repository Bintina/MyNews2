package com.bintina.mynews


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.bintina.mynews.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.bintina.mynews.news.controller.PagerAdapter
import com.bintina.mynews.notifications.NotificationsActivity
import com.bintina.mynews.search.controller.SearchActivity
import com.bintina.mynews.search.controller.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val searchBtn = findViewById<View>(R.id.search_btn)

        setupViewPager()

        searchBtn.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupViewPager() {
        binding.pager.adapter = PagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = when (position) {
                0 -> "Top Stories"
                1 -> "Popular"
                2 -> "Business"
                3 -> "Arts"
                4 -> "Science"
                else -> throw IllegalStateException("Unexpected position $position")
            }
        }
            .attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.notifications_btn -> {
                Log.d("MainActivityBeforeAction Log", "Notifications button clicked")
                val intent = Intent(this, NotificationsActivity::class.java)
                startActivity(intent)

                Log.d("MainActivity", "Notifications button clicked")


                Toast.makeText(this, "notification item clicked", Toast.LENGTH_SHORT).show()
                return true
            }
            // Add more cases for other menu items if needed
            // R.id.other_menu_item_id -> { /* Handle other menu items */ }
            else -> return super.onOptionsItemSelected(item)

        }
    }
}