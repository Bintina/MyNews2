package com.bintina.mynews.features.notifications.controller

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.bintina.mynews.R
import com.bintina.mynews.databinding.ActivityDisplayNotificationsBinding
import com.bintina.mynews.features.notifications.NotificationsDisplayViewModel

class NotificationDisplayActivity : AppCompatActivity() {
    lateinit var viewModel: NotificationsDisplayViewModel
    lateinit var binding: ActivityDisplayNotificationsBinding

    companion object {
        const val KEY_NOTIFICATION_DISPLAY_FRAGMENT = "KEY_NOTIFICATION_DISPLAY_FRAGMENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(NotificationsDisplayViewModel::class.java)

        Log.d("NotificationDisplayActivityLog", "Activity Notifications Display created")
        val notificationDisplayFragment = NotificationsDisplayFragment()
        //onNewIntent(intent)
        Log.d("NDActivityLog", "new intent = $intent")


        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(
            R.id.notification_display_fragment_container,
            notificationDisplayFragment,
            KEY_NOTIFICATION_DISPLAY_FRAGMENT
        )
        transaction.commit()
        Log.d("NDActivityLog", "Fragment commited")
    }


}