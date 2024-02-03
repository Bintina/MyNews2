package com.bintina.mynews.notifications.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bintina.mynews.databinding.FragmentDisplayNotificationsBinding
import com.bintina.mynews.common.model.search.Doc
import com.bintina.mynews.news.controller.OnNewsClickedListener
import com.bintina.mynews.common.util.getSelectedFilters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.bintina.mynews.common.data.DataSource
import com.bintina.mynews.common.util.MyApp
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanArts
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanBusiness
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanEntreprenuers
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanPolitics
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanSports
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanTravel
import com.bintina.mynews.common.util.MyApp.Companion.notificationKeyword
import com.bintina.mynews.notifications.view.adapter.Adapter

/**
 * Fragment responsible for displaying notifications.
 */
class NotificationsDisplayFragment : Fragment(), OnNewsClickedListener {
    // Adapter for displaying notification items
    lateinit var adapter: Adapter

    // View Binding for the fragment
    private var _binding: FragmentDisplayNotificationsBinding? = null
    private val binding get() = _binding!!

    /**
     * Called to create the view for the fragment.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDisplayNotificationsBinding.inflate(inflater, container, false)
        initializeView()
        return binding.root
    }

    /**
     * Called when the fragment is resumed.
     */
    override fun onResume() {
        super.onResume()

        // Retrieving notification global values
        val notificationKeyword = notificationKeyword
        val arts = notificationBooleanArts
        val business = notificationBooleanBusiness
        val entreprenuers = notificationBooleanEntreprenuers
        val politics = notificationBooleanPolitics
        val sports = notificationBooleanSports
        val travel = notificationBooleanTravel

        // Generating filters based on selected Checkbox Booleans
        val filters: String? =
            getSelectedFilters(arts, business, entreprenuers, politics, sports, travel)

        // Fetching notification results from the data source using coroutines
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val result = DataSource.loadNotificationResults(notificationKeyword, filters)
                withContext(Dispatchers.Main) {

                    // Updating the adapter with the fetched results
                    adapter.notificationsResultList = result as MutableList<Doc?>
                    adapter.notifyDataSetChanged()

                }
            } catch (e: Exception) {
                emptyList<Doc?>()
                Toast.makeText(requireContext(), "Sorry, $e Error has occurred.", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    /**
     * Opens the link in a web browser when a notification item is clicked.
     */
    override fun openLink(link: String) {
        val newsSite = Uri.parse(link)
        val intent = Intent(Intent.ACTION_VIEW, newsSite)

        // Add the clicked article to the clickedArticles list and update the adapter
        MyApp.clickedArticles.add(link)
        adapter.notifyDataSetChanged()

        startActivity(intent)
    }

    /**
     * Called when the fragment is destroyed.
     */
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    /**
     * Initializes the view by setting up the adapter and RecyclerView.
     */
    private fun initializeView() {
        adapter = Adapter()
        binding.notificationResultsRecyclerview.adapter = adapter
        adapter.listener = this

    }
}


