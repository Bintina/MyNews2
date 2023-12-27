package com.bintina.mynews.notifications.controller

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.work.Data
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.bintina.mynews.MainActivity
import com.bintina.mynews.databinding.FragmentNewsBinding
import com.bintina.mynews.databinding.FragmentNotificationsBinding
import com.bintina.mynews.notifications.work.NotificationWorker
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_ARTS
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_BUSINESS
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_ENTREPRENUERS
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_KEYWORD
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_POLITICS
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_SPORTS
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_TRAVEL
import com.bintina.mynews.util.MyApp.Companion.notificationBooleanArts
import com.bintina.mynews.util.MyApp.Companion.notificationBooleanBusiness
import com.bintina.mynews.util.MyApp.Companion.notificationBooleanEntreprenuers
import com.bintina.mynews.util.MyApp.Companion.notificationBooleanPolitics
import com.bintina.mynews.util.MyApp.Companion.notificationBooleanSports
import com.bintina.mynews.util.MyApp.Companion.notificationBooleanTravel
import com.bintina.mynews.util.MyApp.Companion.notificationKeyword
class NotificationsFragment: Fragment() {
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    lateinit var listener: OnNotificationsClickedListener

    companion object {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        binding.toggleBtn.setOnClickListener{
            extractNotificationData()

        }


        return binding.root
    }

    private fun extractNotificationData() {

        notificationKeyword = binding.notificationSearchQueryTermEditText.text.toString()
        notificationBooleanArts = binding.notificationCheckboxArts.isChecked
        notificationBooleanBusiness = binding.notificationCheckboxBusiness.isChecked
        notificationBooleanEntreprenuers = binding.notificationCheckboxEntreprenuers.isChecked
        notificationBooleanPolitics = binding.notificationCheckboxPolitics.isChecked
        notificationBooleanSports = binding.notificationCheckboxSports.isChecked
        notificationBooleanTravel = binding.notificationCheckboxTravel.isChecked

        listener.onNotificationsClick()


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

        
}
