package com.bintina.mynews.search.controller

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bintina.mynews.common.util.MyApp.Companion.defaultSearchEndDate
import com.bintina.mynews.common.util.MyApp.Companion.defaultSearchStartDate
import com.bintina.mynews.common.util.MyApp.Companion.enteredSearchEndDate
import com.bintina.mynews.common.util.MyApp.Companion.enteredSearchStartDate
import com.bintina.mynews.common.util.MyApp.Companion.searchBooleanArts
import com.bintina.mynews.common.util.MyApp.Companion.searchBooleanBusiness
import com.bintina.mynews.common.util.MyApp.Companion.searchBooleanEntreprenuers
import com.bintina.mynews.common.util.MyApp.Companion.searchBooleanPolitics
import com.bintina.mynews.common.util.MyApp.Companion.searchBooleanSports
import com.bintina.mynews.common.util.MyApp.Companion.searchBooleanTravel
import com.bintina.mynews.common.util.MyApp.Companion.searchKeyword
import com.bintina.mynews.databinding.FragmentSearchArticlesBinding
import java.util.Calendar

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchArticlesBinding? = null
    private val binding get() = _binding!!
    lateinit var listener: OnSearchClicked


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchArticlesBinding.inflate(inflater, container, false)


        binding.startDateEt.setOnClickListener {
            showStartDatePicker(requireContext(), binding.startDateEt)

        }

        binding.endDateEt.setOnClickListener {
            showEndDatePicker(requireContext(), binding.endDateEt)
        }

        binding.searchBtn.setOnClickListener {
            extractData()
        }
        return binding.root
    }

    private fun extractData() {
        searchKeyword = binding.searchQueryTermEditText.text.toString()
        //Check for null startDate entry
        if (enteredSearchStartDate.isNullOrBlank()) {
            defaultSearchStartDate
        }
        //Check for null endDate entry
        if (enteredSearchEndDate.isNullOrBlank()) {
            defaultSearchEndDate
        }
        //Checkbox values
        searchBooleanArts = binding.checkboxArts.isChecked
        searchBooleanBusiness = binding.checkboxBusiness.isChecked
        searchBooleanEntreprenuers = binding.checkboxEntreprenuers.isChecked
        searchBooleanPolitics = binding.checkboxPolitics.isChecked
        searchBooleanSports = binding.checkboxSports.isChecked
        searchBooleanTravel = binding.checkboxTravel.isChecked

        listener.onSearchClick()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun showStartDatePicker(context: Context, view: TextView) {
        val currentDate = Calendar.getInstance()

        currentDate.add(Calendar.YEAR, -5)

        val initialStartYear = currentDate.get(Calendar.YEAR)
        val initialStartMonth = currentDate.get(Calendar.MONTH)
        val initialStartDay = currentDate.get(Calendar.DAY_OF_MONTH)


        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { view, year, month, dayOfMonth ->
                // Handle the selected date
                val selectedStartDate = String.format("%d-%02d-%02d", year, month + 1, dayOfMonth)
                // Update the TextView or perform any other action

                binding.startDateEt.text = selectedStartDate
                enteredSearchStartDate = selectedStartDate
                Log.d("SearchFragLog","entered start day = $enteredSearchStartDate")
            },
            // Set initial date in the date picker
            initialStartYear, initialStartMonth, initialStartDay
        )

        // Show the date picker dialog
        datePickerDialog.show()


    }

    fun showEndDatePicker(context: Context, view: View) {
        val currentDate = Calendar.getInstance()
        val initialEndYear = currentDate.get(Calendar.YEAR)
        val initialEndMonth = currentDate.get(Calendar.MONTH)
        val initialEndDay = currentDate.get(Calendar.DAY_OF_MONTH)


        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { view, year, month, dayOfMonth ->
                // Handle the selected date
                val selectedEndDate = String.format("%d-%02d-%02d", year, month + 1, dayOfMonth)
                // Update the TextView or perform any other action
                binding.endDateEt.text = selectedEndDate
                enteredSearchEndDate = selectedEndDate
                Log.d("SearchFragLog","entered end day = $enteredSearchEndDate")

            },
            // Set initial date in the date picker
            initialEndYear, initialEndMonth, initialEndDay
        )

        // Show the date picker dialog
        datePickerDialog.show()

    }

}