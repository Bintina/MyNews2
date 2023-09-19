package com.bintina.mynews.topstories.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bintina.mynews.data.DataSource
import com.bintina.mynews.databinding.FragmentTopStoriesBinding
import com.bintina.mynews.topstories.adapter.Adapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TopStoriesFragment: Fragment() {
    lateinit var adapter: Adapter

    private var _binding: FragmentTopStoriesBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopStoriesBinding.inflate(inflater, container, false)
        initializeList()

        lifecycleScope.launch(Dispatchers.IO) {
            val result = DataSource.loadTopStories()
            withContext(Dispatchers.Main){
                if (result != null){
                    adapter.topStoriesList = result
                    adapter.notifyDataSetChanged()
                }
            }
        }
        return binding.root
    }

    private fun initializeList() {
        adapter = Adapter()
        binding.topRecyclerview.adapter = adapter
    }
}