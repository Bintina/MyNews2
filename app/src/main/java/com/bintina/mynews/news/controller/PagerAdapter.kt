package com.bintina.mynews.news.controller

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bintina.mynews.common.util.MyApp.Companion.CURRENT_NEWS_STATE
import com.bintina.mynews.news.view.ArtNewsFragment
import com.bintina.mynews.news.view.BusinessNewsFragment
import com.bintina.mynews.news.view.PopularNewsFragment
import com.bintina.mynews.news.view.ScienceNewsFragment
import com.bintina.mynews.news.view.TopStoriesFragment

/**
 * Pager adapter for managing fragments in the ViewPager.
 *
 * @param activity The hosting FragmentActivity.
 */
class PagerAdapter(activity: Fragment) : FragmentStateAdapter(activity) {

    /**
     * Returns the number of items in the data set.
     */
    override fun getItemCount(): Int {
        return 5
    }

    /**
     * Calls onCreateView with the new CURRENT_NEWS_STATE value.
     *
     * @param position The position of the item within the adapter's data set.
     * @return The NewsFragment to display for the given position and CURRENT_NEWS_STATE.
     */
    override fun createFragment(position: Int): Fragment {
        // Update the current news state based on the selected position
        position.also { CURRENT_NEWS_STATE = it }

        // Return the appropriate fragment content based on the position
        return when (position) {
            0 -> TopStoriesFragment()
            1 -> PopularNewsFragment()
            2 -> BusinessNewsFragment()
            3 -> ArtNewsFragment()
            4 -> ScienceNewsFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }


}