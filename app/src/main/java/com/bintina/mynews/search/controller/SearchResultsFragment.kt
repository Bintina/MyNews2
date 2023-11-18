package com.bintina.mynews.search.controller

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bintina.mynews.databinding.FragmentSearchResultBinding
import com.bintina.mynews.news.adapter.OnNewsClickedListener

class SearchResultsFragment: Fragment(), OnNewsClickedListener {
    lateinit var adapter: com.bintina.mynews.news.adapter.Adapter

private var _binding: FragmentSearchResultBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchResultBinding.inflate(inflater,container, false)
        initializeView()

        TODO("Comunicate with DataSource")

        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initializeView() {
        adapter = com.bintina.mynews.news.adapter.Adapter()
        binding.resultsRecyclerview.adapter = adapter
    }

    override fun openLink(clickedNewsLink: String) {
        val newsSite = Uri.parse(clickedNewsLink)
        val intent = Intent(Intent.ACTION_VIEW, newsSite)

        startActivity(intent)
    }
}