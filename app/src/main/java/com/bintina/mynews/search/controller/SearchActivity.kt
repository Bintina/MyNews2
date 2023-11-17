package com.bintina.mynews.search.controller

import android.os.Bundle
import android.text.TextUtils.replace
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.bintina.mynews.R
import com.bintina.mynews.databinding.ActivitySearchBinding

class SearchActivity: AppCompatActivity() {
    lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<SearchFragment>(R.id.search_fragment_container)
            }


        }
    }
}
