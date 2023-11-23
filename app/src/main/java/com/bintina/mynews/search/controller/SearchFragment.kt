package com.bintina.mynews.search.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bintina.mynews.databinding.FragmentSearchArticlesBinding

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchArticlesBinding
 lateinit var listener: OnSearchClicked
    companion object {
        const val KEY_KEYWORD = "KEY_KEYWORD"
        const val KEY_SPORTS = "KEY_SPORTS"
        const val KEY_POLITICS = "KEY_POLITICS"

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchArticlesBinding.inflate(inflater, container, false)


        binding.searchBtn.setOnClickListener{
            extractFromData()

        }
        return binding.root
    }

    private fun extractFromData() {
        val keyword = binding.searchQueryTermEditText.text.toString()
        val sports = binding.checkboxSports.isChecked
        val politics = binding.checkboxPolitics.isChecked

        val bundle = Bundle()
        bundle.putString(KEY_KEYWORD, keyword)
        bundle.putBoolean(KEY_SPORTS, sports)
        bundle.putBoolean(KEY_POLITICS, politics)

listener.onSearchClick(bundle)
    }


    override fun onDestroy() {
        super.onDestroy()
    }

}