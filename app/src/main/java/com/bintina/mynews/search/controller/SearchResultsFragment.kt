package com.bintina.mynews.search.controller

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bintina.mynews.databinding.FragmentSearchResultBinding
import com.bintina.mynews.news.adapter.Adapter
import com.bintina.mynews.news.adapter.OnNewsClickedListener
import com.bintina.mynews.news.data.DataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchResultsFragment : Fragment(), OnNewsClickedListener {
    lateinit var adapter: com.bintina.mynews.search.adapter.Adapter

    private var _binding: FragmentSearchResultBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchResultBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val bundle = arguments
        bundle?.let {
            val keyword = it.getString(SearchFragment.KEY_KEYWORD)
            val sports = it.getBoolean(SearchFragment.KEY_SPORTS)
            val politics = it.getBoolean(SearchFragment.KEY_POLITICS)

            lifecycleScope.launch(Dispatchers.IO) {
                val result = DataSource.loadSearchResults(keyword)
                Log.d("SearchResultFragLog", "$keyword")


                withContext(Dispatchers.Main) {
                    adapter.searchResultList = result
                    adapter.notifyDataSetChanged()
                    Log.d("Result Fragment", "${result.size} ")
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initializeView() {
        adapter = com.bintina.mynews.search.adapter.Adapter()
        binding.resultsRecyclerview.adapter = adapter
        adapter.listener = this

    }

    override fun openLink(clickedNewsLink: String) {
        val newsSite = Uri.parse(clickedNewsLink)
        val intent = Intent(Intent.ACTION_VIEW, newsSite)

        startActivity(intent)
    }
}