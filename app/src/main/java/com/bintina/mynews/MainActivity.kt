package com.bintina.mynews

import android.app.ActionBar
import android.app.ActionBar.NAVIGATION_MODE_STANDARD
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.util.Linkify
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.viewpager.widget.PagerAdapter
import com.bintina.mynews.business.controller.BusinessNewsFragment
import com.bintina.mynews.databinding.ActivityMainBinding
import com.bintina.mynews.popular.controller.PopularNewsFragment
import com.bintina.mynews.topstories.adapter.OnNewsClickedListener
import com.bintina.mynews.topstories.controller.TopStoriesFragment
import com.google.android.material.navigation.NavigationBarMenu
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : FragmentActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()

    }

    private fun setupViewPager() {
        binding.pager.adapter = PagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = when (position) {
                0 -> "Top Stories"
                1 -> "Popular"
                2 -> "Business"
                else -> throw IllegalStateException("Unexpected position $position")
            }
        }
            .attach()
    }


}