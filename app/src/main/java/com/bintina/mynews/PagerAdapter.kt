package com.bintina.mynews

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bintina.mynews.business.controller.BusinessNewsFragment
import com.bintina.mynews.popular.controller.PopularNewsFragment
import com.bintina.mynews.topstories.controller.TopStoriesFragment

class PagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> TopStoriesFragment()
            1 -> PopularNewsFragment()
            2 -> BusinessNewsFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }

}