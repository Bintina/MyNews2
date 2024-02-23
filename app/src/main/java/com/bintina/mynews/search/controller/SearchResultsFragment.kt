package com.bintina.mynews.search.controller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bintina.mynews.R
import com.bintina.mynews.common.data.repository.DataSource
import com.bintina.mynews.common.model.search.Doc
import com.bintina.mynews.common.util.MyApp
import com.bintina.mynews.common.util.MyApp.Companion.searchBooleanArts
import com.bintina.mynews.common.util.MyApp.Companion.searchBooleanBusiness
import com.bintina.mynews.common.util.MyApp.Companion.searchBooleanEntreprenuers
import com.bintina.mynews.common.util.MyApp.Companion.searchBooleanPolitics
import com.bintina.mynews.common.util.MyApp.Companion.searchBooleanSports
import com.bintina.mynews.common.util.MyApp.Companion.searchBooleanTravel
import com.bintina.mynews.common.util.MyApp.Companion.searchEndDate
import com.bintina.mynews.common.util.MyApp.Companion.searchKeyword
import com.bintina.mynews.common.util.MyApp.Companion.searchStartDate
import com.bintina.mynews.common.util.getSelectedFilters
import com.bintina.mynews.databinding.FragmentSearchResultBinding
import com.bintina.mynews.news.controller.OnNewsClickedListener
import com.bintina.mynews.news.view.WebViewActivity
import com.bintina.mynews.search.SearchViewModel
import com.bintina.mynews.search.view.adapter.Adapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Fragment responsible for displaying search results based on user input.
 */
class SearchResultsFragment : Fragment(), OnNewsClickedListener {
    private lateinit var viewModel: SearchViewModel

    // Adapter for displaying search results
    private lateinit var adapter: Adapter

    // Binding for the fragment
    private var _binding: FragmentSearchResultBinding? = null
    private val binding get() = _binding!!

    /**
     * Called to create and return the view hierarchy associated with the fragment.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchResultBinding.inflate(inflater, container, false)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)


        initializeView()

        // Observe LiveData from ViewModel and update the Adapter
        viewModel.newResultList.observe(viewLifecycleOwner, Observer { newList ->
            adapter.searchResultList = newList ?: emptyList()
            adapter.notifyDataSetChanged()  // Notify the adapter that the data has changed
        })
        Log.d("SearchResultFragLog", "onCreateView Called")
        return binding.root
    }

    /*    */
    /**
     * Called when the fragment is resumed.
     *//*
    override fun onResume() {
        super.onResume()
        val progressIndicator = binding.searchCircularProgressIndicator
        progressIndicator.visibility = View.VISIBLE



    }*/


    /**
     * Called when the fragment is being destroyed.
     */
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    /**
     * Initializes the view components, including the adapter for the RecyclerView.
     */
    private fun initializeView() {
        val progressIndicator = binding.searchCircularProgressIndicator
        progressIndicator.visibility = View.VISIBLE

        adapter = com.bintina.mynews.search.view.adapter.Adapter()
        binding.resultsRecyclerview.adapter = adapter
        adapter.listener = this
        Log.d("SearchResFragLog", "adapter initialised")
        if (MyApp.searchResultList.isEmpty()) {
            Toast.makeText(
                requireContext(),
                getString(R.string.no_news),
                Toast.LENGTH_LONG
            ).show()
            progressIndicator.visibility = View.GONE

        }
        progressIndicator.visibility = View.GONE

    }



    /**
     * Opens the link in a web browser when a search result item is clicked.
     */
    override fun openLink(link: String) {
        // Create an Intent to start the new activity
        val intent = Intent(activity, WebViewActivity::class.java)

        // Optionally, you can pass data to the new Activity
        intent.putExtra("newsLinkKey", link)

        // Add the clicked article to the clickedArticles list and update the adapter
        MyApp.clickedArticles.add(link)
        adapter.notifyDataSetChanged()

        // Start the new Activity
        startActivity(intent)
    }


}