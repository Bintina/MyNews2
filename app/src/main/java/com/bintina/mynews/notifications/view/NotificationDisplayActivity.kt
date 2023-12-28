package com.bintina.mynews.notifications.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bintina.mynews.R
import com.bintina.mynews.databinding.ActivityDisplayNotificationsBinding

class NotificationDisplayActivity : AppCompatActivity() {
    lateinit var binding: ActivityDisplayNotificationsBinding

    companion object {
        const val KEY_NOTIFICATION_DISPLAY_FRAGMENT = "KEY_NOTIFICATION_DISPLAY_FRAGMENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

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