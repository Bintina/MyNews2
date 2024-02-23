package com.bintina.mynews.search.controller

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.util.Log.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bintina.mynews.common.data.repository.DataSource
import com.bintina.mynews.common.model.search.Doc
import com.bintina.mynews.common.util.MyApp.Companion.searchEndDate
import com.bintina.mynews.common.util.MyApp.Companion.searchResultList
import com.bintina.mynews.common.util.MyApp.Companion.searchStartDate
import com.bintina.mynews.common.util.getSelectedFilters
import com.bintina.mynews.common.util.getStringDates
import com.bintina.mynews.databinding.FragmentSearchArticlesBinding
import com.bintina.mynews.search.SearchViewModel
import com.bintina.mynews.search.view.adapter.Adapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Date
import java.util.Calendar

/**
 * A fragment for searching articles based on user input.
 */
class SearchFragment : Fragment() {
    private lateinit var viewModel: SearchViewModel
    lateinit var adapter: Adapter

    // Binding for the fragment
    private var _binding: FragmentSearchArticlesBinding? = null
    private val binding get() = _binding!!

    //search key terms
    lateinit var keyword: String
    lateinit var startDate: java.util.Date
    lateinit var endDate: java.util.Date
    var filters: String? = null

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

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        extractSearchTerms()


        // Click listener for start date EditText
        startDate = searchStartDate
        binding.startDateEt.setOnClickListener {
            startDate = showStartDatePicker()
        }
        // Click listener for end date EditText
        endDate = searchEndDate
        binding.endDateEt.setOnClickListener {
            endDate = showEndDatePicker()
        }

Log.d("SearchFragLog", "dates are $startDate and $endDate")
        viewModel.setSearchDates(startDate, endDate)

Log.d("SearchFragLog", "onCreateView reached end")
        return binding.root
    }

    /**
     * Extracts data from UI elements.
     */
    fun extractSearchTerms(): String? {
        // Retrieve search parameters from user entries
        keyword = binding.searchQueryTermEditText.text.toString()
Log.d("SearchFragLog", "extractSearchTerms keyword is $keyword")

        //Checkbox values
        val arts = binding.checkboxArts.isChecked
        val business = binding.checkboxBusiness.isChecked
        val entreprenuers = binding.checkboxEntreprenuers.isChecked
        val politics = binding.checkboxPolitics.isChecked
        val sports = binding.checkboxSports.isChecked
        val travel = binding.checkboxTravel.isChecked

        // Get selected filters
        filters = viewModel.setFilters(arts, business, entreprenuers, politics, sports, travel)
Log.d("SearchFragLog", "extractSearchTerms ran")
        return filters

    }



    /**
     * Called when the fragment is being destroyed.
     */
    override fun onDestroy() {
keyword = binding.searchQueryTermEditText.text.toString()
        viewModel._keyword.value = keyword
Log.d("SearchFragLog", "keyword is $keyword")
        super.onDestroy()
        _binding = null
    }

    fun goToSearch(){


    }

    /**
     * Shows the date picker dialog for selecting the start date.
     */
    fun showStartDatePicker(): java.util.Date {
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
                val selectedStartDate =
                    Date(calendar.timeInMillis)        // possible bug fix type change: val selectedStartDate = LocalDate.of(year, month + 1, dayOfMonth)
                // Update the TextView and save selected date
                binding.startDateEt.text = getStringDates(selectedStartDate, "dd/MM/yy")

                if (selectedStartDate != null) {
                    startDate = selectedStartDate
                } else {
                    startDate = searchStartDate
                }
            },
            // Set initial date in the date picker
            initialStartYear, initialStartMonth, initialStartDay
        )

        // Show the date picker dialog
        datePickerDialog.show()
        return startDate
    }

    /**
     * Shows the date picker dialog for selecting the end date.
     */
    fun showEndDatePicker(): java.util.Date {
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
                val selectedEndDate =
                    Date(calendar.timeInMillis)                // possible bug fix type change: val selectedStartDate = LocalDate.of(year, month + 1, dayOfMonth)
                // Update the TextView and save selected date.
                binding.endDateEt.text = getStringDates(selectedEndDate, "dd/MM/yy")

                if (selectedEndDate != null) {
                    endDate = selectedEndDate
                } else {
                    endDate = searchEndDate
                }
            },
            // Set initial date in the date picker
            initialEndYear, initialEndMonth, initialEndDay
        )

        // Show the date picker dialog
        datePickerDialog.show()
        return endDate
    }
}