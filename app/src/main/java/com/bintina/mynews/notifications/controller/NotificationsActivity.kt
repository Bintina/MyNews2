package com.bintina.mynews.notifications.controller

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bintina.mynews.R
import com.bintina.mynews.databinding.ActivityNotificationsBinding

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
Log.d("NotificationOnCreatLog","Navigation Activity onCreate onCreated")
        val notificationsFragment = NotificationsFragment()
        notificationsFragment.listener = this

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.notification_fragment_container, notificationsFragment, KEY_NOTIFICATION_FRAGMENT)
        transaction.commit()
    }

    override fun onNotificationsClick(bundle: Bundle) {
        val notificationDisplayFragment = NotificationsDisplayFragment()
        notificationDisplayFragment.arguments = bundle

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(
            R.id.notification_fragment_container,
            notificationDisplayFragment   ,
            NotificationsActivity.KEY_NOTIFICATION_DISPLAY_FRAGMENT
        )
        transaction.commit()
    }
}