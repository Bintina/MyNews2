package com.bintina.mynews.notifications.controller

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.bintina.mynews.R
import com.bintina.mynews.databinding.ActivityNotificationsBinding
import com.bintina.mynews.notifications.controller.work.NotificationWorker
import com.bintina.mynews.common.util.Constants
import com.bintina.mynews.common.util.Constants.CHANNEL_ID
import com.bintina.mynews.common.util.Constants.NOTIFICATIONS_WORK_NAME
import com.bintina.mynews.common.util.Constants.NOTIFICATION_CHANNEL_DESCRIPTION
import com.bintina.mynews.common.util.Constants.NOTIFICATION_CHANNEL_NAME
import com.bintina.mynews.common.util.Constants.NOTIFICATION_KEY_ARTS
import com.bintina.mynews.common.util.Constants.NOTIFICATION_KEY_BUSINESS
import com.bintina.mynews.common.util.Constants.NOTIFICATION_KEY_ENTREPRENUERS
import com.bintina.mynews.common.util.Constants.NOTIFICATION_KEY_KEYWORD
import com.bintina.mynews.common.util.Constants.NOTIFICATION_KEY_POLITICS
import com.bintina.mynews.common.util.Constants.NOTIFICATION_KEY_SPORTS
import com.bintina.mynews.common.util.Constants.NOTIFICATION_KEY_TRAVEL
import com.bintina.mynews.common.util.Constants.NOTIFICATION_TITLE
import com.bintina.mynews.common.util.MyApp
import com.bintina.mynews.common.util.openAboutActivity
import com.bintina.mynews.common.util.openHelpActivity
import com.bintina.mynews.common.util.openNotificationsActivity
import com.bintina.mynews.common.util.openSearchActivity
import java.util.concurrent.TimeUnit

class NotificationsActivity : AppCompatActivity(), OnNotificationsClickedListener {
    lateinit var binding: ActivityNotificationsBinding

    companion object {
        const val KEY_NOTIFICATION_FRAGMENT = "KEY_NOTIFICATION_FRAGMENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(findViewById(R.id.my_toolbar))
        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        toolbar.setBackgroundColor(ContextCompat.getColor(this, com.google.android.material.R.color.design_default_color_secondary))

        val searchBtn = findViewById<View>(R.id.search_btn)
        searchBtn.setOnClickListener {
            openSearchActivity()
        }

        Log.d("NotificationOnCreateLog", "Navigation Activity onCreate onCreated")
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


    override fun onNotificationsClick() {
        val notificationWorkRequest: WorkRequest =
            PeriodicWorkRequestBuilder<NotificationWorker>(1, TimeUnit.HOURS)
                .build()

        WorkManager
            .getInstance(applicationContext)
            .enqueue(notificationWorkRequest)
        Log.d("NotificationWorkerRequestLog", "Worker Request sent from Notification Activity")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true    }
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

            else -> return super.onOptionsItemSelected(item)
        }
    }

}