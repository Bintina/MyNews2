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
import com.bintina.mynews.common.util.openAboutActivity
import com.bintina.mynews.common.util.openHelpActivity
import com.bintina.mynews.common.util.openNotificationsActivity
import com.bintina.mynews.common.util.openSearchActivity
import com.bintina.mynews.databinding.ActivityAboutBinding
import com.bintina.mynews.news.controller.OnNewsClickedListener

class AboutActivity : AppCompatActivity(), OnNewsClickedListener {
    lateinit var binding: ActivityAboutBinding
    lateinit var listener : OnNewsClickedListener
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("AboutActivityLog", "About Activity onCreate called.")
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(findViewById(R.id.my_toolbar))
        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        toolbar.setBackgroundColor(
            ContextCompat.getColor(
                this,
                com.google.android.material.R.color.design_default_color_secondary
            )
        )

        val searchBtn = findViewById<View>(R.id.search_btn)
        searchBtn.setOnClickListener {
            openSearchActivity()
        }


     val ocLink = getString(R.string.open_classrooms_link)
        binding.aboutContent.setOnClickListener {
           openLink(ocLink)
        }
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

    override fun openLink(link: String) {
        val ocSite = Uri.parse(link)
        val intent = Intent(Intent.ACTION_VIEW, ocSite)

        startActivity(intent)
    }
}