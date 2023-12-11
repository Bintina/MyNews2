package com.bintina.mynews.search.controller

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bintina.mynews.databinding.FragmentSearchArticlesBinding
import java.util.Calendar

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchArticlesBinding? = null
    private val binding get() = _binding!!
    lateinit var listener: OnSearchClicked

    lateinit var enteredStartDate: String
    lateinit var enteredEndDate: String

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
        _binding = FragmentSearchArticlesBinding.inflate(inflater, container, false)

        binding.startDateEt.setOnClickListener {
            showStartDatePicker(requireContext(), binding.startDateEt)

        }

        binding.endDateEt.setOnClickListener {
            showEndDatePicker(requireContext(), binding.endDateEt)
        }

        binding.searchBtn.setOnClickListener {
            extractFromData(requireContext())
        }
        return binding.root
    }

    private fun extractFromData(context: Context) {
        val keyword = binding.searchQueryTermEditText.text.toString()
        val startDate = enteredStartDate
        val endDate = enteredEndDate
        val sports = binding.checkboxSports.isChecked
        val politics = binding.checkboxPolitics.isChecked
        val arts = binding.checkboxArts.isChecked
        val entreprenuers = binding.checkboxEntreprenuers.isChecked
        val business = binding.checkboxBusiness.isChecked
        val travel = binding.checkboxTravel.isChecked

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
                enteredStartDate = selectedStartDate
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
                enteredEndDate = selectedEndDate

            },
            // Set initial date in the date picker
            initialEndYear, initialEndMonth, initialEndDay
        )

        // Show the date picker dialog
        datePickerDialog.show()

    }

}