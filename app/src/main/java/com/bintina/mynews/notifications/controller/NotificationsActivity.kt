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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.bintina.mynews.R
import com.bintina.mynews.databinding.ActivityNotificationsBinding
import com.bintina.mynews.notifications.work.NotificationWorker
import com.bintina.mynews.util.Constants
import com.bintina.mynews.util.Constants.CHANNEL_ID
import com.bintina.mynews.util.Constants.NOTIFICATIONS_WORK_NAME
import com.bintina.mynews.util.Constants.NOTIFICATION_CHANNEL_DESCRIPTION
import com.bintina.mynews.util.Constants.NOTIFICATION_CHANNEL_NAME
import com.bintina.mynews.util.Constants.NOTIFICATION_TITLE
import java.util.concurrent.TimeUnit

class NotificationsActivity: AppCompatActivity(), OnNotificationsClickedListener {
    lateinit var binding: ActivityNotificationsBinding

    companion object {
        const val KEY_NOTIFICATION_FRAGMENT = "KEY_NOTIFICATION_FRAGMENT"
        const val KEY_NOTIFICATION_DISPLAY_FRAGMENT = "KEY_NOTIFICATION_DISPLAY_FRAGMENT"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Log.d("NotificationOnCreateLog","Navigation Activity onCreate onCreated")
        val notificationsFragment = NotificationsFragment()
        notificationsFragment.listener = this

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.notification_fragment_container, notificationsFragment, KEY_NOTIFICATION_FRAGMENT)
        transaction.commit()


    onNewIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        //get data from bundles using intent
        val bundle: Bundle? = intent!!.extras
       /* if (bundle != null){
            val name = getString("KEY_NAME")
        }*/
    }
    override fun onNotificationsClick(bundle: Bundle) {
        val notificationWorkRequest: WorkRequest =
            OneTimeWorkRequestBuilder<NotificationWorker>()
                .build()
Log.d("NotificationClickLog","onNotificationsClick called")
        WorkManager
            .getInstance(applicationContext)
            .enqueue(notificationWorkRequest)

    }



    override fun notifyUser(bundle: Bundle) {
        val intent = Intent(this, NotificationsActivity::class.java)
        startActivity(intent)

        val notificationsDisplayFragment = NotificationsDisplayFragment()


        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.notification_fragment_container, notificationsDisplayFragment, KEY_NOTIFICATION_DISPLAY_FRAGMENT)
        transaction.commit()
    }


}