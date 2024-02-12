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
import com.bintina.mynews.common.util.goHome
import com.bintina.mynews.common.util.openAboutActivity
import com.bintina.mynews.common.util.openHelpActivity
import com.bintina.mynews.common.util.openNotificationsActivity
import com.bintina.mynews.common.util.openSearchActivity
import com.bintina.mynews.databinding.ActivityDisplayNotificationsBinding

/**
 * Activity responsible for displaying notifications.
 */
class NotificationDisplayActivity : AppCompatActivity() {
    // View Binding for the activity
    lateinit var binding: ActivityDisplayNotificationsBinding

    companion object {
        // Key for the notification display fragment
        const val KEY_NOTIFICATION_DISPLAY_FRAGMENT = "KEY_NOTIFICATION_DISPLAY_FRAGMENT"
    }

    /**
     * Called when the activity is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setting up the Toolbar and setting it's background color
        setSupportActionBar(findViewById(R.id.my_toolbar))
        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        toolbar.setBackgroundColor(
            ContextCompat.getColor(
                this,
                com.google.android.material.R.color.design_default_color_secondary
            )
        )

        // Setting up a click listener for the search button
        val searchBtn = findViewById<View>(R.id.menu_search_btn)
        searchBtn.setOnClickListener {
            openSearchActivity()
        }

        //Setting up the fragment and starting a FragmentTransaction
        val notificationDisplayFragment = NotificationsDisplayFragment()
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(
            R.id.notification_display_fragment_container,
            notificationDisplayFragment,
            KEY_NOTIFICATION_DISPLAY_FRAGMENT
        )
        transaction.commit()
        Log.d("NDActivityLog", "transaction.commit() called in onCreate")
    }

    // Creating options menu in the toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    // Handling menu item clicks in the toolbar
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