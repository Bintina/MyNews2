package com.bintina.mynews

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bintina.mynews.util.MyApp.Companion.CURRENT_NEWS_STATE


class PagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {


    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
//CURRENT_NEWS_FRAGMENT = position
        position.also { CURRENT_NEWS_STATE = it }

        return when (position){
            0 -> NewsFragment()
            1 -> NewsFragment()
            2 -> NewsFragment()
            3 -> NewsFragment()
            4 -> NewsFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }



}