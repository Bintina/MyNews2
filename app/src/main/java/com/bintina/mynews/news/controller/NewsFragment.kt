package com.bintina.mynews.news.controller

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bintina.mynews.R
import com.bintina.mynews.common.data.repository.DataSource
import com.bintina.mynews.databinding.FragmentNewsBinding
import com.bintina.mynews.news.view.adapter.Adapter
import com.bintina.mynews.common.util.MyApp.Companion.CURRENT_NEWS_STATE
import com.bintina.mynews.common.util.MyApp.Companion.clickedArticles
import com.bintina.mynews.news.view.WebViewActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
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
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)



        initializeList()

        // Asynchronously load news data
        lifecycleScope.launch(Dispatchers.IO) {
 /*           if(DataSource.loadNews() = isActive){

            }*/
            val result = DataSource.loadNews()
            println("before coroutine size${result.size}")
            withContext(Dispatchers.Main) {
            println("after ${result.size}")
                if (result.isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.no_news),
                        Toast.LENGTH_LONG
                    ).show()

                } else {

                    adapter.storiesList = result
                    adapter.notifyDataSetChanged()

                }
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

    override fun openLink(link: String) {
        // Create an Intent to start the new activity
        val intent = Intent(activity, WebViewActivity::class.java)

        // Optionally, you can pass data to the new Activity
        intent.putExtra("newsLinkKey", link)

        // Start the new Activity
        startActivity(intent)
        // Add the clicked article to the clickedArticles list and update the adapter
        clickedArticles.add(link)
        adapter.notifyDataSetChanged()

        startActivity(intent)
    }

}