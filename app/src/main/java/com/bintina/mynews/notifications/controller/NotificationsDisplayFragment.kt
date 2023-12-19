package com.bintina.mynews.notifications.controller

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
import com.bintina.mynews.model.search.Doc
import com.bintina.mynews.news.adapter.OnNewsClickedListener
import com.bintina.mynews.search.adapter.Adapter
import com.bintina.mynews.util.objectToPreference
import com.bintina.mynews.util.getSelectedFilters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.bintina.mynews.data.DataSource

class NotificationsDisplayFragment : Fragment(), OnNewsClickedListener {
    private var _binding: FragmentDisplayNotificationsBinding? = null
    private val binding get() = _binding!!

    lateinit var adapter: Adapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDisplayNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val bundle = arguments
        bundle?.let {
            val notificationKeyword = it.getString(NotificationsFragment.NOTIFICATION_KEY_KEYWORD)
            val arts = it.getBoolean(NotificationsFragment.NOTIFICATION_KEY_ARTS)
            val business = it.getBoolean(NotificationsFragment.NOTIFICATION_KEY_BUSINESS)
            val entreprenuers = it.getBoolean(NotificationsFragment.NOTIFICATION_KEY_ENTREPRENUERS)
            val politics = it.getBoolean(NotificationsFragment.NOTIFICATION_KEY_POLITICS)
            val sports = it.getBoolean(NotificationsFragment.NOTIFICATION_KEY_SPORTS)
            val travel = it.getBoolean(NotificationsFragment.NOTIFICATION_KEY_TRAVEL)

            val filters: String? =
                getSelectedFilters(arts, business, entreprenuers, politics, sports, travel)

            lifecycleScope.launch(Dispatchers.IO) {
                val result = try {
                    DataSource.loadNotificationResults(notificationKeyword, filters)
                } catch (e: Exception) {
                    emptyList<Doc?>()
                    Log.d("NotificationsResultsTryCatch", "Error is $e")
                }

                withContext(Dispatchers.Main) {
                    adapter.searchResultList = result as MutableList<Doc?>
                    adapter.notifyDataSetChanged()
                    Log.d("Notification Fragment", "${result.size}")
                }
            }
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeView()
    }
    private fun initializeView() {
        adapter = com.bintina.mynews.search.adapter.Adapter()
        binding.resultsRecyclerview.adapter = adapter
        adapter.listener = this

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun openLink(clickedNewsLink: String) {
        val newsSite = Uri.parse(clickedNewsLink)
        val intent = Intent(Intent.ACTION_VIEW, newsSite)

        startActivity(intent)
    }
}