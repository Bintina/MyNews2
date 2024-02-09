/*
package com.bintina.mynews.news.view

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.bintina.mynews.common.util.MyApp
import com.bintina.mynews.databinding.FragmentSearchArticlesBinding
import com.bintina.mynews.databinding.FragmentWebViewBinding
import com.bintina.mynews.search.controller.OnSearchClicked

class WebViewFragment: Fragment() {
    // Binding for the fragment
    private var _binding: FragmentWebViewBinding? = null
    private val binding get() = _binding!!

    val newsLink = MyApp.clickedNewsLink

    // Listener for search click events
    lateinit var listener: OnSearchClicked

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWebViewBinding.inflate(inflater,container, false)

        loadNewsLink(newsLink)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun loadNewsLink(link: String) {
        val parsedLink = Uri.parse(link)
        val myWebView: WebView = binding.webview
        myWebView.loadUrl(parsedLink.toString())

        */
/*        val myWebViewWithContext = WebView(activityContext)
                setContentView(myWebViewWithContext)*//*

    }
}*/
