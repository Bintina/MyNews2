package com.bintina.mynews.search.controller

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bintina.mynews.common.data.DataSource
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
import com.bintina.mynews.search.view.adapter.Adapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Fragment responsible for displaying search results based on user input.
 */
class SearchResultsFragment : Fragment(), OnNewsClickedListener {

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
        initializeView()

        return binding.root
    }

    /**
     * Called when the fragment is resumed.
     */
    override fun onResume() {
        super.onResume()

        // Retrieve search parameters from global variables
        val keyword = searchKeyword
        val startDate = searchStartDate
        val endDate = searchEndDate
        val arts = searchBooleanArts
        val business = searchBooleanBusiness
        val entreprenuers = searchBooleanEntreprenuers
        val politics = searchBooleanPolitics
        val sports = searchBooleanSports
        val travel = searchBooleanTravel

        // Get selected filters
        val filters: String? =
            getSelectedFilters(arts, business, entreprenuers, politics, sports, travel)

        // Use coroutines to perform the search operation in the background
        lifecycleScope.launch(Dispatchers.IO) {
            val result = try {
                DataSource.loadSearchResults(keyword, startDate, endDate, filters)
            } catch (e: Exception) {
                Log.d("SearchResultTryCatch", "Error is $e")
                Toast.makeText(
                    requireContext(),
                    "Sorry, we do not have results for this search at the moment. Please try a wider search.",
                    Toast.LENGTH_LONG
                ).show()
                emptyList<Doc?>()
            }

            // Update the UI with the search results
            withContext(Dispatchers.Main) {
                adapter.searchResultList = result.toMutableList()
                adapter.notifyDataSetChanged()
                Log.d(
                    "Result Fragment",
                    "${result.size}, Start date is $startDate and End date is $endDate"
                )
            }

        }
    }


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
        adapter = Adapter()
        binding.resultsRecyclerview.adapter = adapter
        adapter.listener = this

    }

    /**
     * Opens the link in a web browser when a search result item is clicked.
     */
    override fun openLink(link: String) {
        val newsSite = Uri.parse(link)
        val intent = Intent(Intent.ACTION_VIEW, newsSite)

        // Add the clicked article to the clickedArticles list and update the adapter
        MyApp.clickedArticles.add(link)
        adapter.notifyDataSetChanged()

        startActivity(intent)
    }


}