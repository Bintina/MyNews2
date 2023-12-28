package com.bintina.mynews.features.notifications.controller

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bintina.mynews.databinding.FragmentDisplayNotificationsBinding
import com.bintina.mynews.data.model.search.Doc
import com.bintina.mynews.features.news.adapter.OnNewsClickedListener
import com.bintina.mynews.util.getSelectedFilters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.bintina.mynews.data.DataSource
import com.bintina.mynews.MyApp.Companion.notificationBooleanArts
import com.bintina.mynews.MyApp.Companion.notificationBooleanBusiness
import com.bintina.mynews.MyApp.Companion.notificationBooleanEntreprenuers
import com.bintina.mynews.MyApp.Companion.notificationBooleanPolitics
import com.bintina.mynews.MyApp.Companion.notificationBooleanSports
import com.bintina.mynews.MyApp.Companion.notificationBooleanTravel
import com.bintina.mynews.MyApp.Companion.notificationKeyword


class NotificationsDisplayFragment : Fragment(), OnNewsClickedListener {
    lateinit var adapter: com.bintina.mynews.features.notifications.adapter.Adapter
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
        adapter = com.bintina.mynews.features.notifications.adapter.Adapter()
        binding.notificationResultsRecyclerview.adapter = adapter
        adapter.listener = this

    }
}


