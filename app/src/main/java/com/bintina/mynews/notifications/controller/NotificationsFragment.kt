package com.bintina.mynews.notifications.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bintina.mynews.common.util.MyApp.Companion.currentDate
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanArts
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanBusiness
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanEntreprenuers
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanPolitics
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanSports
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanTravel
import com.bintina.mynews.common.util.MyApp.Companion.notificationKeyword
import com.bintina.mynews.common.util.getDefaultNotificationStartDate
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
        notificationKeyword = binding.notificationSearchQueryTermEditText.text.toString()
        notificationBooleanArts = binding.notificationCheckboxArts.isChecked
        notificationBooleanBusiness = binding.notificationCheckboxBusiness.isChecked
        notificationBooleanEntreprenuers = binding.notificationCheckboxEntreprenuers.isChecked
        notificationBooleanPolitics = binding.notificationCheckboxPolitics.isChecked
        notificationBooleanSports = binding.notificationCheckboxSports.isChecked
        notificationBooleanTravel = binding.notificationCheckboxTravel.isChecked

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
