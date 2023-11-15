package com.bintina.mynews

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bintina.mynews.data.DataSource
import com.bintina.mynews.databinding.FragmentNewsBinding
import com.bintina.mynews.adapter.Adapter
import com.bintina.mynews.adapter.OnNewsClickedListener
import com.bintina.mynews.util.MyApp.Companion.CURRENT_NEWS_STATE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsFragment: Fragment(CURRENT_NEWS_STATE), OnNewsClickedListener {

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
         val result = DataSource.loadNews()
         withContext(Dispatchers.Main){
             if (result != null){
                 adapter.storiesList = result
                 adapter.notifyDataSetChanged()
             }
         }
     }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    private fun initializeList() {
        adapter = Adapter()
        binding.recyclerview.adapter = adapter
        adapter.listener = this

    }
    override fun openLink(clickedNewsLink: String) {
        val newsSite = Uri.parse(clickedNewsLink)
        val intent = Intent(Intent.ACTION_VIEW, newsSite)

        startActivity(intent)
    }
}