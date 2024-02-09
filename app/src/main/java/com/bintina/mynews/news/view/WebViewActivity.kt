package com.bintina.mynews.news.view

import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.bintina.mynews.R
import com.bintina.mynews.common.util.MyApp
import com.bintina.mynews.common.util.goHome
import com.bintina.mynews.common.util.openAboutActivity
import com.bintina.mynews.common.util.openHelpActivity
import com.bintina.mynews.common.util.openNotificationsActivity
import com.bintina.mynews.common.util.openSearchActivity
import com.bintina.mynews.databinding.ActivityWebViewBinding
import com.bintina.mynews.search.controller.SearchActivity
import com.bintina.mynews.search.controller.SearchFragment


class WebViewActivity : AppCompatActivity() {

    lateinit var binding: ActivityWebViewBinding

    companion object {
        const val KEY_WEB_VIEW_FRAGMENT = "KEY_WEB_VIEW_FRAGMENT"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the search button click listener
        val searchBtn = findViewById<View>(R.id.menu_search_btn)
        searchBtn.setOnClickListener {
            openSearchActivity()
        }

        // Set up the toolbar
        setSupportActionBar(findViewById(R.id.my_toolbar))
        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        toolbar.setBackgroundColor(
            ContextCompat.getColor(
                this,
                com.google.android.material.R.color.design_default_color_secondary
            )
        )

        // Retrieve the Intent that started this activity
        val intent = intent

        // Retrieve the extra data from the Intent
        val extraData = intent.getStringExtra("newsLinkKey")
        if (extraData != null) {
            loadNewsLink(extraData)
        }

        /*     // Create and initialize the initial search fragment
             val webViewFragment = WebViewFragment()

             val fragmentManager = supportFragmentManager
             val transaction = fragmentManager.beginTransaction()
             transaction.add(
                 R.id.web_view_fragment_container, webViewFragment, KEY_WEB_VIEW_FRAGMENT)
             transaction.commit()
     */

    }

    /**
     * Initialize the options menu.
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

    private fun loadNewsLink(link: String) {
        val parsedLink = Uri.parse(link)
        val myWebView: WebView = binding.webview
        myWebView.loadUrl(parsedLink.toString())

        /*        val myWebViewWithContext = WebView(activityContext)
                setContentView(myWebViewWithContext)*/
    }

}