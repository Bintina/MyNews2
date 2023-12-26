package com.bintina.mynews.notifications.controller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bintina.mynews.R
import com.bintina.mynews.databinding.ActivityDisplayNotificationsBinding
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_ARTS
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_BUSINESS
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_ENTREPRENUERS
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_KEYWORD
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_POLITICS
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_SPORTS
import com.bintina.mynews.util.Constants.NOTIFICATION_KEY_TRAVEL
import com.bintina.mynews.util.MyApp.Companion.notificationBooleanArts
import com.bintina.mynews.util.MyApp.Companion.notificationBooleanBusiness
import com.bintina.mynews.util.MyApp.Companion.notificationBooleanEntreprenuers
import com.bintina.mynews.util.MyApp.Companion.notificationBooleanPolitics
import com.bintina.mynews.util.MyApp.Companion.notificationBooleanSports
import com.bintina.mynews.util.MyApp.Companion.notificationBooleanTravel
import com.bintina.mynews.util.MyApp.Companion.notificationKeyword

class NotificationDisplayActivity: AppCompatActivity() {
    lateinit var binding: ActivityDisplayNotificationsBinding
companion object{
    const val KEY_NOTIFICATION_DISPLAY_FRAGMENT = "KEY_NOTIFICATION_DISPLAY_FRAGMENT"
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("NotificationDisplayActivityLog","Activity Notifications Display created")
        val notificationDisplayFragment = NotificationsDisplayFragment()
        onNewIntent(intent)
        Log.d("NDActivityLog","new intent = $intent")


        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.notification_display_fragment_container, notificationDisplayFragment, KEY_NOTIFICATION_DISPLAY_FRAGMENT)
        transaction.commit()
Log.d("NDActivityLog", "Fragment commited")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        intent?.let {
            val keyword = it.getStringExtra(NOTIFICATION_KEY_KEYWORD)
            val arts = it.getBooleanExtra(NOTIFICATION_KEY_ARTS, false)
            val business = it.getBooleanExtra(NOTIFICATION_KEY_BUSINESS, false)
            val politics = it.getBooleanExtra(NOTIFICATION_KEY_POLITICS, false)
            val entreprenuers = it.getBooleanExtra(NOTIFICATION_KEY_ENTREPRENUERS, false)
            val sports = it.getBooleanExtra(NOTIFICATION_KEY_SPORTS, false)
            val travel = it.getBooleanExtra(NOTIFICATION_KEY_TRAVEL, false)

            val fragment = NotificationsDisplayFragment()

            val bundle =  Bundle()
            fragment.arguments= bundle
        }


    }

}