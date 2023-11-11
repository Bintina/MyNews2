package com.bintina.mynews.topstoriesapi.arts.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bintina.mynews.data.DataSource

import com.bintina.mynews.databinding.FragmentNewsBinding
import com.bintina.mynews.topstoriesapi.adapter.Adapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtsFragment: Fragment() {
    lateinit var adapter: Adapter

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        initializeList()

        lifecycleScope.launch(Dispatchers.IO) {
            val result = DataSource.loadArtStories()
            withContext(Dispatchers.Main){
                if (result != null){
                    adapter.storiesList = result
                    adapter.notifyDataSetChanged()
                }
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun initializeList() {
        adapter = Adapter()
        binding.recyclerview.adapter = adapter
    }
}