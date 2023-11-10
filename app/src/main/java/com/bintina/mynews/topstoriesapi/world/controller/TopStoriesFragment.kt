package com.bintina.mynews.topstoriesapi.world.controller

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bintina.mynews.data.DataSource
import com.bintina.mynews.databinding.FragmentTopStoriesBinding
import com.bintina.mynews.topstoriesapi.adapter.Adapter
import com.bintina.mynews.topstoriesapi.adapter.OnNewsClickedListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TopStoriesFragment : Fragment(), OnNewsClickedListener {
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
            withContext(Dispatchers.Main) {
                if (result != null) {
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
        adapter.listener = this
    }

    override fun openLink(newsClicked: String) {
        val newsSite = Uri.parse(newsClicked)
        val intent = Intent(Intent.ACTION_VIEW, newsSite)

        startActivity(intent)
    }


}