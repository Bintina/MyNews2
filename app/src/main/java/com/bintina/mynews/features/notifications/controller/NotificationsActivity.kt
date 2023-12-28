package com.bintina.mynews.features.notifications.controller

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.bintina.mynews.R
import com.bintina.mynews.databinding.ActivityNotificationsBinding
import com.bintina.mynews.features.notifications.NotificationsViewModel
import com.bintina.mynews.features.notifications.work.NotificationWorker
import com.bintina.mynews.util.Constants
import com.bintina.mynews.util.Constants.CHANNEL_ID
import com.bintina.mynews.util.Constants.NOTIFICATIONS_WORK_NAME
import com.bintina.mynews.util.Constants.NOTIFICATION_CHANNEL_DESCRIPTION
import com.bintina.mynews.util.Constants.NOTIFICATION_CHANNEL_NAME
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_ARTS
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_BUSINESS
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_ENTREPRENUERS
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_KEYWORD
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_POLITICS
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_SPORTS
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_TRAVEL
import com.bintina.mynews.util.Constants.NOTIFICATION_TITLE
import java.util.concurrent.TimeUnit

class NotificationsActivity : AppCompatActivity(), OnNotificationsClickedListener {
    lateinit var viewModel: NotificationsViewModel
    lateinit var binding: ActivityNotificationsBinding

    companion object {
        const val KEY_NOTIFICATION_FRAGMENT = "KEY_NOTIFICATION_FRAGMENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)


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


}