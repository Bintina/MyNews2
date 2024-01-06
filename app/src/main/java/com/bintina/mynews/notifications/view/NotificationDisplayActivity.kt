package com.bintina.mynews.notifications.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.bintina.mynews.R
import com.bintina.mynews.common.util.MyApp
import com.bintina.mynews.common.util.openAboutActivity
import com.bintina.mynews.common.util.openHelpActivity
import com.bintina.mynews.common.util.openNotificationsActivity
import com.bintina.mynews.common.util.openSearchActivity
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

        setSupportActionBar(findViewById(R.id.my_toolbar))
        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        toolbar.setBackgroundColor(ContextCompat.getColor(this, com.google.android.material.R.color.design_default_color_secondary))


        Log.d("NotificationDisplayActivityLog", "Activity Notifications Display created")
        val notificationDisplayFragment = NotificationsDisplayFragment()
        //onNewIntent(intent)
        Log.d("NDActivityLog", "new intent = $intent")

        val searchBtn = findViewById<View>(R.id.search_btn)
        searchBtn.setOnClickListener {
            openSearchActivity()
        }

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
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