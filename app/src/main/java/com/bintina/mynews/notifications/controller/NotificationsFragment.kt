package com.bintina.mynews.notifications.controller

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bintina.mynews.common.util.MyApp
import com.bintina.mynews.common.util.MyApp.Companion.currentDate
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanArts
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanBusiness
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanEntreprenuers
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanPolitics
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanSports
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanTravel
import com.bintina.mynews.common.util.MyApp.Companion.notificationKeyword
import com.bintina.mynews.common.util.getDefaultNotificationStartDate
import com.bintina.mynews.common.util.getSelectedFilters
import com.bintina.mynews.common.util.stringToPreference
import com.bintina.mynews.databinding.FragmentNotificationsBinding

/**
 * Fragment responsible for managing notification settings.
 */
class NotificationsFragment : Fragment() {
    // View Binding for the fragment
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    // Listener for notifications click events
    lateinit var listener: OnNotificationsClickedListener

    companion object {

    }

    /**
     * Called when the fragment view is created.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        // Set default notification start date
        getDefaultNotificationStartDate(currentDate)

        // Set up click listener for the toggle button
        binding.toggleBtn.setOnClickListener {
            extractNotificationData()
        }

        return binding.root
    }

    /**
     * Extracts notification data and invokes the click event.
     */
    private fun extractNotificationData() {
        // Extract notification data from UI elements
        val notificationKeyword = binding.notificationSearchQueryTermEditText.text.toString()
        val notificationBooleanArts = binding.notificationCheckboxArts.isChecked
        val notificationBooleanBusiness = binding.notificationCheckboxBusiness.isChecked
        val notificationBooleanEntreprenuers = binding.notificationCheckboxEntreprenuers.isChecked
        val notificationBooleanPolitics = binding.notificationCheckboxPolitics.isChecked
        val notificationBooleanSports = binding.notificationCheckboxSports.isChecked
        val notificationBooleanTravel = binding.notificationCheckboxTravel.isChecked
        Log.d("NotificationFragmentLog","keyword is $notificationKeyword" )

        val filters = getSelectedFilters(
            notificationBooleanArts,
            notificationBooleanBusiness,
            notificationBooleanEntreprenuers,
            notificationBooleanPolitics,
            notificationBooleanSports,
            notificationBooleanTravel
        )
        Log.d("NotificationFragmentLog","filters are $filters" )
        stringToPreference(requireContext(), notificationKeyword, MyApp.QUERY_TERM)

        if(filters!= null) {
            stringToPreference(requireContext(), filters, MyApp.FILTERS)
        }

        // Notify the listener about the notifications click event
        listener.onNotificationsClick()


    }

    /**
     * Called when the fragment is destroyed.
     */
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
