package com.bintina.mynews.overflow.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bintina.mynews.databinding.ActivityAboutBinding

class AboutActivity: AppCompatActivity() {
    lateinit var binding : ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("AboutActivityLog", "About Activity onCreate called.")
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}