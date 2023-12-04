package com.bintina.mynews.search.controller

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bintina.mynews.databinding.FragmentSearchArticlesBinding
import com.bintina.mynews.util.showEndDatePicker
import com.bintina.mynews.util.showStartDatePicker

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchArticlesBinding
    lateinit var listener: OnSearchClicked

    companion object {
        const val KEY_KEYWORD = "KEY_KEYWORD"
        const val START_DATE = "START_DATE"
        const val END_DATE = "END_DATE"
        const val KEY_SPORTS = "KEY_SPORTS"
        const val KEY_POLITICS = "KEY_POLITICS"

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchArticlesBinding.inflate(inflater, container, false)

        binding.startDateEt.setOnClickListener{
            showStartDatePicker(requireContext(), binding.startDateEt)
        }

        binding.endDateEt.setOnClickListener{
            showEndDatePicker(requireContext(), binding.endDateEt)
        }

        binding.searchBtn.setOnClickListener {
            extractFromData(requireContext())
        }
        return binding.root
    }

    private fun extractFromData(context: Context) {
        val keyword = binding.searchQueryTermEditText.text.toString()
        val startDate = showStartDatePicker(context, binding.startDateEt)
        val endDate = showStartDatePicker(context, binding.endDateEt)
        val sports = binding.checkboxSports.isChecked
        val politics = binding.checkboxPolitics.isChecked

        val bundle = Bundle()
        bundle.putString(KEY_KEYWORD, keyword)
        bundle.putString(START_DATE, startDate)
        bundle.putString(END_DATE, endDate)
        bundle.putBoolean(KEY_SPORTS, sports)
        bundle.putBoolean(KEY_POLITICS, politics)

        listener.onSearchClick(bundle)
    }


    override fun onDestroy() {
        super.onDestroy()
    }


}