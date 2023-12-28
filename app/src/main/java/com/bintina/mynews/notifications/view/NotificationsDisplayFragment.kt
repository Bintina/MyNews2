package com.bintina.mynews.notifications.controller

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.work.Data
import com.bintina.mynews.databinding.FragmentDisplayNotificationsBinding
import com.bintina.mynews.common.model.search.Doc
import com.bintina.mynews.news.adapter.OnNewsClickedListener
import com.bintina.mynews.search.adapter.Adapter
import com.bintina.mynews.common.util.objectToPreference
import com.bintina.mynews.common.util.getSelectedFilters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.bintina.mynews.common.data.DataSource
import com.bintina.mynews.common.util.Constants.NOTIFICATION_KEY_KEYWORD
import com.bintina.mynews.common.util.Constants.NOTIFICATION_KEY_ARTS
import com.bintina.mynews.common.util.Constants.NOTIFICATION_KEY_BUSINESS
import com.bintina.mynews.common.util.Constants.NOTIFICATION_KEY_ENTREPRENUERS
import com.bintina.mynews.common.util.Constants.NOTIFICATION_KEY_POLITICS
import com.bintina.mynews.common.util.Constants.NOTIFICATION_KEY_SPORTS
import com.bintina.mynews.common.util.Constants.NOTIFICATION_KEY_TRAVEL
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanArts
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanBusiness
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanEntreprenuers
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanPolitics
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanSports
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanTravel
import com.bintina.mynews.common.util.MyApp.Companion.notificationKeyword


class NotificationsDisplayFragment : Fragment(), OnNewsClickedListener {
    lateinit var adapter: com.bintina.mynews.notifications.adapter.Adapter
    private var _binding: FragmentDisplayNotificationsBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDisplayNotificationsBinding.inflate(inflater, container, false)
        initializeView()

        return binding.root
    }

    override fun onResume() {
        Log.d("NDFonResumeLog", "NotificationsDisplayFragment onResume called")
        super.onResume()



        val notificationKeyword = notificationKeyword
        val arts = notificationBooleanArts
        val business =  notificationBooleanBusiness
        val entreprenuers = notificationBooleanEntreprenuers
        val politics = notificationBooleanPolitics
        val sports = notificationBooleanSports
        val travel =  notificationBooleanTravel

        Log.d(
            "NDFValues",
            "keyword = $notificationKeyword artsBoolean = $arts, businessB = $business, entreprenuersB = $entreprenuers, politicsB = $politics, sportsB= $sports, travelB = $travel"
        )
        val filters: String? =
            getSelectedFilters(arts, business, entreprenuers, politics, sports, travel)
        Log.d("NotificationDFbundleLog", "filters = $filters")

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val result = DataSource.loadNotificationResults(notificationKeyword, filters)
                withContext(Dispatchers.Main) {

                    Log.d("Notification Fragment", "size before adapter${result.size}")
                    adapter.notificationsResultList = result as MutableList<Doc?>
                    adapter.notifyDataSetChanged()
                    Log.d("Notification Fragment", "size after adapter${result.size}")
                }
            } catch (e: Exception) {
                emptyList<Doc?>()
                Log.d("NotificationsResultsTryCatch", "Error is $e")
            }

        }
    }

    override fun openLink(clickedNewsLink: String) {
        val newsSite = Uri.parse(clickedNewsLink)
        val intent = Intent(Intent.ACTION_VIEW, newsSite)

        // ContextCompat.startActivity(intent)
        TODO("Debug open link")
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initializeView() {
        adapter = com.bintina.mynews.notifications.adapter.Adapter()
        binding.notificationResultsRecyclerview.adapter = adapter
        adapter.listener = this

    }
}


