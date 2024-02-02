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
import com.bintina.mynews.common.util.MyApp.Companion.currentDate
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
import com.bintina.mynews.common.util.MyApp.Companion.searchEndDate
import com.bintina.mynews.common.util.MyApp.Companion.searchKeyword
import com.bintina.mynews.common.util.MyApp.Companion.searchStartDate
import com.bintina.mynews.common.util.getDefaultSearchStartDate
import com.bintina.mynews.common.util.getStringDates
import com.bintina.mynews.common.util.instantiateTodaysDate
import com.bintina.mynews.databinding.FragmentSearchArticlesBinding
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * A fragment for searching articles based on user input.
 */
class SearchFragment : Fragment() {
    // Binding for the fragment
    private var _binding: FragmentSearchArticlesBinding? = null
    private val binding get() = _binding!!

    // Listener for search click events
    lateinit var listener: OnSearchClicked

    /**
     * Called to create and return the view hierarchy associated with the fragment.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchArticlesBinding.inflate(inflater, container, false)

        //instantiate date
        instantiateTodaysDate()

        // Set default search start date
        getDefaultSearchStartDate(currentDate)

        // Click listener for start date EditText
        binding.startDateEt.setOnClickListener {
            showStartDatePicker()
        }

        // Click listener for end date EditText
        binding.endDateEt.setOnClickListener {
            showEndDatePicker()
        }

        // Click listener for search button
        binding.startSearchBtn.setOnClickListener {
            extractData()
        }
        return binding.root
    }

    /**
     * Extracts data from UI elements and triggers the search.
     */
    private fun extractData() {
        searchKeyword = binding.searchQueryTermEditText.text.toString()

        //Check for null startDate entry
        if (enteredSearchStartDate.isNullOrBlank()) {
            defaultSearchStartDate
        }

        //Check for null endDate entry
        if (enteredSearchEndDate.isBlank()) {
            defaultSearchEndDate
        }

        //Checkbox values
        searchBooleanArts = binding.checkboxArts.isChecked
        searchBooleanBusiness = binding.checkboxBusiness.isChecked
        searchBooleanEntreprenuers = binding.checkboxEntreprenuers.isChecked
        searchBooleanPolitics = binding.checkboxPolitics.isChecked
        searchBooleanSports = binding.checkboxSports.isChecked
        searchBooleanTravel = binding.checkboxTravel.isChecked

        // Notify the listener about the search click
        listener.onSearchClick()
    }

    /**
     * Called when the fragment is being destroyed.
     */
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    /**
     * Shows the date picker dialog for selecting the start date.
     */
    fun showStartDatePicker() {
        // Calendar instance for handling dates
        val currentDate = Calendar.getInstance()
        currentDate.add(Calendar.YEAR, -5)

        // Initial date values for the date picker
        val initialStartYear = currentDate.get(Calendar.YEAR)
        val initialStartMonth = currentDate.get(Calendar.MONTH)
        val initialStartDay = currentDate.get(Calendar.DAY_OF_MONTH)

        // Create a date picker dialog
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                // Handle the selected date
                val calendar = Calendar.getInstance()
                calendar.set(year, month, dayOfMonth)

                // Convert the Calendar's timeInMillis to a Date
                val selectedStartDate = Date(calendar.timeInMillis)        // possible bug fix type change: val selectedStartDate = LocalDate.of(year, month + 1, dayOfMonth)
                // Update the TextView and save selected date
                binding.startDateEt.text = getStringDates(selectedStartDate, "dd/MM/yy")
                searchStartDate = selectedStartDate
            },
            // Set initial date in the date picker
            initialStartYear, initialStartMonth, initialStartDay
        )

        // Show the date picker dialog
        datePickerDialog.show()
    }

    /**
     * Shows the date picker dialog for selecting the end date.
     */
    fun showEndDatePicker() {
        // Calendar instance for handling dates
        val currentDate = Calendar.getInstance()

        // Initial date values for the date picker
        val initialEndYear = currentDate.get(Calendar.YEAR)
        val initialEndMonth = currentDate.get(Calendar.MONTH)
        val initialEndDay = currentDate.get(Calendar.DAY_OF_MONTH)

        // Create a date picker dialog
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                // Handle the selected date
                val calendar = Calendar.getInstance()
                calendar.set(year, month, dayOfMonth)

                // Convert the Calendar's timeInMillis to a Date
                val selectedEndDate = Date(calendar.timeInMillis)                // possible bug fix type change: val selectedStartDate = LocalDate.of(year, month + 1, dayOfMonth)
                // Update the TextView and save selected date.
                binding.endDateEt.text = getStringDates(selectedEndDate, "dd/MM/yy")
                searchEndDate = selectedEndDate
            },
            // Set initial date in the date picker
            initialEndYear, initialEndMonth, initialEndDay
        )

        // Show the date picker dialog
        datePickerDialog.show()
    }
}