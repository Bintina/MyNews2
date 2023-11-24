package com.bintina.mynews.search.controller

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.bintina.mynews.R
import com.bintina.mynews.databinding.ActivitySearchBinding
import com.bintina.mynews.search.controller.SearchActivity.Companion.KEY_SEARCH_FRAGMENT_RESULTS

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

        val searchFragment = SearchFragment()
        searchFragment.listener = this

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.search_fragment_container, searchFragment, KEY_SEARCH_FRAGMENT)
        transaction.commit()


    }


    override fun onSearchClick(bundle: Bundle) {
        val searchResultsFragment = SearchResultsFragment()
        searchResultsFragment.arguments = bundle

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(
            R.id.search_fragment_container,
            searchResultsFragment,
            KEY_SEARCH_FRAGMENT_RESULTS
        )
        transaction.commit()
    }
}