package com.bintina.mynews.notifications.controller

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.bintina.mynews.R
import com.bintina.mynews.common.util.goHome
import com.bintina.mynews.common.util.instantiateTodaysDate
import com.bintina.mynews.common.util.openAboutActivity
import com.bintina.mynews.common.util.openHelpActivity
import com.bintina.mynews.common.util.openNotificationsActivity
import com.bintina.mynews.common.util.openSearchActivity
import com.bintina.mynews.databinding.ActivityNotificationsBinding
import com.bintina.mynews.notifications.controller.work.NotificationWorker
import java.util.concurrent.TimeUnit

/**
 * Activity responsible for managing notification settings.
 */
class NotificationsActivity : AppCompatActivity(), OnNotificationsClickedListener {
    // View Binding for the activity
    lateinit var binding: ActivityNotificationsBinding

    companion object {
        // Key for the notification fragment
        const val KEY_NOTIFICATION_FRAGMENT = "KEY_NOTIFICATION_FRAGMENT"
    }

    /**
     * Called when the activity is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instantiateTodaysDate()

        // Set up the toolbar
        setSupportActionBar(findViewById(R.id.my_toolbar))
        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        toolbar.setBackgroundColor(
            ContextCompat.getColor(
                this,
                com.google.android.material.R.color.design_default_color_secondary
            )
        )

        // Set up the search button click listener
        val searchBtn = findViewById<View>(R.id.menu_search_btn)
        searchBtn.setOnClickListener {
            openSearchActivity()
        }

        // Set up the NotificationsFragment
        val notificationsFragment = NotificationsFragment()
        notificationsFragment.listener = this

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(
            R.id.notification_fragment_container,
            notificationsFragment,
            KEY_NOTIFICATION_FRAGMENT
        )
        transaction.commit()
    }

    /**
     * Called when the notifications are clicked.
     * Initiates the periodic work request for notifications.
     */
    override fun onNotificationsClick() {
        val notificationWorkRequest: WorkRequest =
            PeriodicWorkRequestBuilder<NotificationWorker>(24, TimeUnit.HOURS)
                .build()

        WorkManager
            .getInstance(applicationContext)
            .enqueue(notificationWorkRequest)
        Log.d("NotificationWorkerRequestLog", "Worker Request sent from Notification Activity")
    }

    /**
     * Initialize the options menu.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    /**
     * Handle menu item selections.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.notifications_btn -> {
                openNotificationsActivity()
                return true
            }

            R.id.help_btn -> {
                openHelpActivity()
                return true
            }

            R.id.about_btn -> {
                openAboutActivity()
                return true
            }

            R.id.home_btn -> {
                goHome()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

}