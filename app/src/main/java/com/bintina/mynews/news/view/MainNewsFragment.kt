package com.bintina.mynews.news.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bintina.mynews.common.util.instantiateTodaysDate
import com.bintina.mynews.databinding.FragmentMainNewsBinding
import com.bintina.mynews.news.controller.PagerAdapter
import com.bintina.mynews.news.view.adapter.Adapter
import com.google.android.material.tabs.TabLayoutMediator

class MainNewsFragment : Fragment() {
    lateinit var navController: NavController
    lateinit var adapter: Adapter

    private var _binding: FragmentMainNewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainNewsBinding.inflate(inflater, container, false)
        adapter = Adapter()
        //instantiate navController

       navController = findNavController()

        instantiateTodaysDate()

        //Set up the ViewPager with TabLayout
        setupViewPager()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
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
            //tab.view.setBackgroundColor(resources.getColor(R.color.design_default_color_secondary))

        }
            .attach()
    }
}