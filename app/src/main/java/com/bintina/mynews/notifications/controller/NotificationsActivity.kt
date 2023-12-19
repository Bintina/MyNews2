package com.bintina.mynews.notifications.controller

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.bintina.mynews.R
import com.bintina.mynews.databinding.ActivityNotificationsBinding

class NotificationsActivity: AppCompatActivity() {
    lateinit var binding: ActivityNotificationsBinding

    companion object {
        const val KEY_NOTIFICATION_FRAGMENT = "KEY_NOTIFICATION_FRAGMENT"
    }
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notificationsFragment = NotificationsFragment()

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.notification_fragment_container, notificationsFragment, KEY_NOTIFICATION_FRAGMENT)
        transaction.commit()
    }
}