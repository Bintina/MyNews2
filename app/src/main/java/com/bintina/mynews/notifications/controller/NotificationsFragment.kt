package com.bintina.mynews.notifications.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bintina.mynews.databinding.FragmentNewsBinding
import com.bintina.mynews.databinding.FragmentNotificationsBinding

class NotificationsFragment: Fragment() {
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    lateinit var listener: OnNotificationsClickedListener

    companion object {
        const val NOTIFICATION_KEY_KEYWORD = "KEY_KEYWORD"
        const val NOTIFICATION_KEY_ARTS = "KEY_POLITICS"
        const val NOTIFICATION_KEY_BUSINESS = "KEY_BUSINESS"
        const val NOTIFICATION_KEY_ENTREPRENUERS = "KEY_ENTREPRENUERS"
        const val NOTIFICATION_KEY_POLITICS = "KEY_POLITICS"
        const val NOTIFICATION_KEY_SPORTS = "KEY_SPORTS"
        const val NOTIFICATION_KEY_TRAVEL = "KEY_TRAVEL"
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
        val notificationKeyword = binding.notificationSearchQueryTermEditText.text.toString()
        val arts = binding.notificationCheckboxArts.isChecked
        val business = binding.notificationCheckboxBusiness.isChecked
        val entreprenuers = binding.notificationCheckboxEntreprenuers.isChecked
        val politics = binding.notificationCheckboxPolitics.isChecked
        val sports = binding.notificationCheckboxSports.isChecked
        val travel = binding.notificationCheckboxTravel.isChecked

        val bundle: Bundle = Bundle()
        bundle.putString(NOTIFICATION_KEY_KEYWORD, notificationKeyword)
        bundle.putBoolean(NOTIFICATION_KEY_ARTS, arts)
        bundle.putBoolean(NOTIFICATION_KEY_BUSINESS, business)
        bundle.putBoolean(NOTIFICATION_KEY_ENTREPRENUERS, entreprenuers)
        bundle.putBoolean(NOTIFICATION_KEY_POLITICS, politics)
        bundle.putBoolean(NOTIFICATION_KEY_SPORTS, sports)
        bundle.putBoolean(NOTIFICATION_KEY_TRAVEL, travel)

        listener.onNotificationsClick(bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}