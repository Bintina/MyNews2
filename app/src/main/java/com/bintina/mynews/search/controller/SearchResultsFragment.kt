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

class SearchResultsFragment : Fragment(), OnNewsClickedListener {
    lateinit var adapter: Adapter

    private var _binding: FragmentSearchResultBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchResultBinding.inflate(inflater, container, false)

        Log.d("SearchResFragLog", "onCreate called")

        return binding.root
    }

    override fun onResume() {
        super.onResume()


        Log.d("SearchResFragLog", "onResume called")

        val keyword = searchKeyword
        val startDate = searchStartDate
        val endDate = searchEndDate
        val arts = searchBooleanArts
        val business = searchBooleanBusiness
        val entreprenuers = searchBooleanEntreprenuers
        val politics = searchBooleanPolitics
        val sports = searchBooleanSports
        val travel = searchBooleanTravel


        val filters: String? =
            getSelectedFilters(arts, business, entreprenuers, politics, sports, travel)

        lifecycleScope.launch(Dispatchers.IO) {
            val result = try {
                DataSource.loadSearchResults(keyword, startDate, endDate, filters)
            } catch (e: Exception) {
                emptyList<Doc?>()
                Log.d("SearchResultTryCatch", "Error is $e")
                Toast.makeText(requireContext(),"Sorry, we do not have results for this search at the moment. Please try a wider search.", Toast.LENGTH_LONG).show()
            }
            //Deal with errors here!!
            Log.d("SearchResultFragLog", "$keyword")


            withContext(Dispatchers.Main) {
                adapter.searchResultList = result as MutableList<Doc?>
                adapter.notifyDataSetChanged()
                Log.d(
                    "Result Fragment",
                    "${result.size}, Start date is $startDate and End date is $endDate"
                )
            }

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initializeView() {
        adapter = Adapter()
        binding.resultsRecyclerview.adapter = adapter
        adapter.listener = this

    }

    override fun openLink(link: String) {
        val newsSite = Uri.parse(link)
        val intent = Intent(Intent.ACTION_VIEW, newsSite)

        startActivity(intent)
    }


}