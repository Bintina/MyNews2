package com.bintina.mynews.news.controller

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bintina.mynews.common.data.DataSource
import com.bintina.mynews.databinding.FragmentNewsBinding
import com.bintina.mynews.news.view.adapter.Adapter
import com.bintina.mynews.news.controller.OnNewsClickedListener
import com.bintina.mynews.common.util.MyApp.Companion.CURRENT_NEWS_STATE
import com.bintina.mynews.common.util.MyApp.Companion.clickedArticles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Fragment displaying news articles
 */
class NewsFragment : Fragment(CURRENT_NEWS_STATE), OnNewsClickedListener {

    lateinit var adapter: Adapter

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    /**
     * Called to create the view for this fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        initializeList()

        // Asynchronously load news data
        lifecycleScope.launch(Dispatchers.IO) {
            val result = DataSource.loadNews()
            withContext(Dispatchers.Main) {
                adapter.storiesList = result
                adapter.notifyDataSetChanged()

            }
        }
        return binding.root
    }

    /**
     * Called when the fragment is no longer in use.
     */
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    /**
     * Initializes the RecyclerView and its Adapter.
     */
    private fun initializeList() {
        adapter = Adapter()
        binding.recyclerview.adapter = adapter
        adapter.listener = this

    }

    /**
     * Opens the provided link in a browser.
     *
     * @param link The link to be opened.
     */
    override fun openLink(link: String) {
        val newsSite = Uri.parse(link)
        val intent = Intent(Intent.ACTION_VIEW, newsSite)

        // Add the clicked article to the clickedArticles list and update the adapter
        clickedArticles.add(link)
        adapter.notifyDataSetChanged()

        startActivity(intent)
    }
}