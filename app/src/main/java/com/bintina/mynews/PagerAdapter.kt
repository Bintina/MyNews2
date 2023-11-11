package com.bintina.mynews

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bintina.mynews.model.News
import com.bintina.mynews.topstoriesapi.business.controller.BusinessNewsFragment
import com.bintina.mynews.popular.controller.PopularNewsFragment
import com.bintina.mynews.topstoriesapi.arts.controller.ArtsFragment
import com.bintina.mynews.topstoriesapi.science.controller.ScienceFragment
import com.bintina.mynews.topstoriesapi.world.controller.TopStoriesFragment
import com.bintina.mynews.util.Constants.fragmentState

class PagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {


    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> NewsFragment()
            1 -> NewsFragment()

            else -> throw IllegalStateException("Unexpected position $position")
        }
    }

}