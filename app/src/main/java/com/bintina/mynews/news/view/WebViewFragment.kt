package com.bintina.mynews.news.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.bintina.mynews.common.util.MyApp
import com.bintina.mynews.databinding.FragmentWebViewBinding

class WebViewFragment : Fragment() {
    var _binding: FragmentWebViewBinding? = null
    val binding get() = _binding!!

    val newsLink = MyApp.clickedNewsLink
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebViewBinding.inflate(inflater, container, false)
        loadNewsLink(newsLink)
        return binding.root
    }

    private fun loadNewsLink(link: String) {
        val myWebView: WebView = binding.webview
        myWebView.loadUrl(link)

        /*        val myWebViewWithContext = WebView(activityContext)
                setContentView(myWebViewWithContext)*/
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}