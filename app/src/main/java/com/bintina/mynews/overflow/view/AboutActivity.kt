package com.bintina.mynews.overflow.view

import android.content.Intent
import android.net.Uri
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
import com.bintina.mynews.databinding.ActivityAboutBinding
import com.bintina.mynews.news.controller.OnNewsClickedListener

/**
 * Activity responsible for displaying information about the application.
 */
class AboutActivity : AppCompatActivity(), OnNewsClickedListener {
    // View Binding for the activity
    lateinit var binding: ActivityAboutBinding

    /**
     * Called when the activity is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("AboutActivityLog", "About Activity onCreate called.")
        super.onCreate(savedInstanceState)
        // Inflate the layout
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the toolbar
        setSupportActionBar(findViewById(R.id.my_toolbar))
        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        toolbar.setBackgroundColor(
            ContextCompat.getColor(
                this,
                com.google.android.material.R.color.design_default_color_secondary
            )
        )

        // Set up the click listener for the search button
        val searchBtn = findViewById<View>(R.id.search_btn)
        searchBtn.setOnClickListener {
            openSearchActivity()
        }

        // Set up the click listener for text. Links to OpenClassrooms
        val ocLink = getString(R.string.open_classrooms_link)
        binding.aboutContent.setOnClickListener {
            openLink(ocLink)
        }
    }

    /**
     * Create the options menu for the activity.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    /**
     * Handle options menu item selection.
     */
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

    /**
     * Open Open Classrooms link override listener method.
     */
    override fun openLink(link: String) {
        val ocSite = Uri.parse(link)
        val intent = Intent(Intent.ACTION_VIEW, ocSite)

        startActivity(intent)
    }
}