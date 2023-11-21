package com.bintina.mynews.search.controller

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import com.bintina.mynews.R
import com.bintina.mynews.databinding.FragmentSearchArticlesBinding
import com.bintina.mynews.search.data.DataSource
import com.bintina.mynews.search.data.DataSource.loadSearchResults
import com.bintina.mynews.util.MyApp
import com.bintina.mynews.util.MyApp.Companion.DATE_RANGE
import com.bintina.mynews.util.MyApp.Companion.FILTERS
import com.bintina.mynews.util.MyApp.Companion.QUERY_TERM
import com.bintina.mynews.util.MyApp.Companion.savedDateRange
import com.bintina.mynews.util.MyApp.Companion.savedFilters
import com.bintina.mynews.util.MyApp.Companion.savedQuery
import com.bintina.mynews.util.MyApp.Companion.searchResults
import com.bintina.mynews.util.stringToPreference

class SearchFragment : Fragment(), OnSearchClickListener {
    private lateinit var binding: FragmentSearchArticlesBinding
    private lateinit var listener: OnSearchClickListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchArticlesBinding.inflate(inflater, container, false)
        this.context?.let { initializeViews(context = it) }

//        navigateToSearchResults()
        return binding.root
    }

/*
    private fun navigateToSearchResults() {

    }
*/


    override fun onDestroy() {
        super.onDestroy()
    }

    private fun initializeViews(context: Context) {
        listener = this
        //Query term
        val queryEtText = binding.searchQueryTermEditText.text.toString()
        println("$queryEtText")
        //Save Query to preferences
        stringToPreference(context, queryEtText, QUERY_TERM)
        Log.d("Pref Log", "query is: $savedQuery")

        //Date Range
        val dateRange =getDateRange()
        //Save Date Range to preferences
        stringToPreference(context,dateRange, DATE_RANGE)
        Log.d("Pref Log", "Dates are: $savedDateRange")

        //Category Booleans
        val filters =getQuerryFilters()
        //Save Filters to preferences
        stringToPreference(context, filters, FILTERS)
        Log.d("Pref Log", "Filters are: $savedFilters")

//Testing code for listeners
        binding.searchBtn.setOnClickListener {

            val resultFragment = SearchResultsFragment()

            childFragmentManager.beginTransaction()
                .replace(R.id.search_fragment_container, resultFragment)
                .commit()
            println("${MyApp.savedQuery} had ${searchResults.size} results")
        }
    }

    private fun getQuerryFilters(): String {
        val artBoolean = boxChecked(binding.checkboxArts)
        Log.d("ArtCheckBox", "Art is checked? $artBoolean")

        val entreprenuerBoolean = boxChecked(binding.checkboxEntreprenuers)
        Log.d("EntrepCheckBox", "Entreprenuers is checked? $entreprenuerBoolean")

        val sportsBoolean = boxChecked(binding.checkboxSports)
        Log.d("SporCheckBox", "Sport is checked? $sportsBoolean")

        val businessBoolean = boxChecked(binding.checkboxBusiness)
        Log.d("BusiCheckBox", "Business is checked? $businessBoolean")

        val politicsBoolean = boxChecked(binding.checkboxPolitics)
        Log.d("PoliCheckBox", "Politics is checked? $politicsBoolean")

        val travelBoolean = boxChecked(binding.checkboxTravel)
        Log.d("TravCheckBox", "Travel is checked? $travelBoolean")

        return "$artBoolean.$entreprenuerBoolean.$sportsBoolean.$businessBoolean.$politicsBoolean.$travelBoolean"
    }

    private fun getDateRange(): String {
        val startDate = binding.startDateEt.text
        val endDate = binding.endDateEt.text
        Log.d("StartDateLog", "Start Date is $startDate")
        Log.d("EndDateLog", "End Date is $endDate")


        return "$startDate-$endDate"
    }

    override fun getQuery(typedQuery: Editable): String {

        return "q=${typedQuery.toString()}"
    }

    override fun getSearchStartDate(startDate: Editable) {
        TODO("Not yet implemented")
    }

    override fun getSearchEndDate(endDate: Editable) {
        TODO("Not yet implemented")
    }

    override fun boxChecked(view: CheckBox): Boolean {
        return view.isChecked
    }

    override fun openResults() {
        TODO("Not yet implemented")
    }

}