package com.bintina.mynews.search.controller

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import com.bintina.mynews.databinding.FragmentSearchArticlesBinding

class SearchFragment : Fragment(), OnSearchClickListener {
    private lateinit var binding: FragmentSearchArticlesBinding
    private lateinit var listener: OnSearchClickListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchArticlesBinding.inflate(inflater, container, false)
        initializeViews()

        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    private fun initializeViews() {
        listener = this
        //Query term
        val queryEtText = binding.searchQueryTermEditText.text
println("$queryEtText")

        //Date Range
        val startDate = binding.startDateEt.text
        val endDate = binding.endDateEt.text
        getDateRange()

        //Category Booleans
        getQuerryFilters()

        binding.searchBtn.setOnClickListener{

            initializeViews()
        }
    }

    private fun getQuerryFilters() {
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
    }

    private fun getDateRange() {
        Log.d("StartDateLog", "Start Date is ${binding.startDateEt.text}")
        Log.d("EndDateLog", "End Date is ${binding.endDateEt.text}")
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

}