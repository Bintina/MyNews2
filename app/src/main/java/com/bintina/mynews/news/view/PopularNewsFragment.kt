package com.bintina.mynews.news.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bintina.mynews.R
import com.bintina.mynews.common.data.repository.DataSource
import com.bintina.mynews.common.model.news.News
import com.bintina.mynews.common.util.MyApp
import com.bintina.mynews.databinding.FragmentNewsBinding
import com.bintina.mynews.news.controller.OnNewsClickedListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PopularNewsFragment: Fragment(), OnNewsClickedListener {
    // Adapter for displaying notification items
    lateinit var adapter: com.bintina.mynews.news.view.adapter.Adapter

    // View Binding for the fragment
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)

        initializeView()

        val progressIndicator = binding.newsCircularProgressIndicator
        progressIndicator.visibility = View.VISIBLE

        lifecycleScope.launch(Dispatchers.Main) {

            try {
                // Use async to launch the coroutine and get a Deferred result
                val popResultDeferred = DataSource.loadPopularNews(lifecycleScope)

                // Wait for the topResultDeferred coroutine to finish and get the result
                val popResult = popResultDeferred.await()

                Log.d("PopFragLog", "pop news has ${popResult.size}")

                if (popResult.isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.no_news),
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    adapter.storiesList = popResult
                    adapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                Log.e("TopFragLog", "Error loading top news", e)
                Toast.makeText(
                    requireContext(),
                    getString(R.string.error_loading_news),
                    Toast.LENGTH_LONG
                ).show()
            } finally {
                progressIndicator.visibility = View.GONE
            }
        }

        Log.d("NewsFragmentLog", "onCreateView Called")
        return binding.root    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initializeView() {
        adapter = com.bintina.mynews.news.view.adapter.Adapter()
        binding.recyclerview.adapter = adapter
        adapter.listener = this
    }

    override fun openLink(link: String) {
        // Create an Intent to start the new activity
        val intent = Intent(activity, WebViewActivity::class.java)

        // Optionally, you can pass data to the new Activity
        intent.putExtra("newsLinkKey", link)

        // Add the clicked article to the clickedArticles list and update the adapter
        MyApp.clickedArticles.add(link)
        adapter.notifyDataSetChanged()

        startActivity(intent)
    }

}