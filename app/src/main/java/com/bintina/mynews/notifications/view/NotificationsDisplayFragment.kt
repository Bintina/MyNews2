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
import com.bintina.mynews.R
import com.bintina.mynews.databinding.FragmentDisplayNotificationsBinding
import com.bintina.mynews.news.controller.OnNewsClickedListener
import com.bintina.mynews.common.util.MyApp
import com.bintina.mynews.common.util.MyApp.Companion.notificationNewsList
import com.bintina.mynews.news.view.WebViewActivity
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

        Log.d("NDFragmentLog", "onCreateView Called")
        return binding.root
    }

    /**
     * Opens the link in a web browser when a notification item is clicked.
     */
    override fun openLink(link: String) {
        // Create an Intent to start the new activity
        val intent = Intent(activity, WebViewActivity::class.java)

        // Optionally, you can pass data to the new Activity
        intent.putExtra("newsLinkKey", link)

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
        val progressIndicator = binding.circularProgressIndicator
        progressIndicator.visibility = View.VISIBLE

        adapter = Adapter()
        binding.notificationResultsRecyclerview.adapter = adapter
        adapter.listener = this
        if (notificationNewsList.isNullOrEmpty()) {
            Toast.makeText(
                requireContext(),
                getString(R.string.no_news),
                Toast.LENGTH_LONG
            ).show()
            progressIndicator.visibility = View.GONE

        }
        progressIndicator.visibility = View.GONE

    }
}


