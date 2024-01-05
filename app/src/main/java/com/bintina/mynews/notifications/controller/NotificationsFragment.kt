package com.bintina.mynews.notifications.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bintina.mynews.common.util.MyApp.Companion.currentDate
import com.bintina.mynews.databinding.FragmentNotificationsBinding
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanArts
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanBusiness
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanEntreprenuers
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanPolitics
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanSports
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanTravel
import com.bintina.mynews.common.util.MyApp.Companion.notificationKeyword
import com.bintina.mynews.common.util.getDefaultNotificationStartDate

class NotificationsFragment : Fragment() {
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

        getDefaultNotificationStartDate(currentDate)

        binding.toggleBtn.setOnClickListener {
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
