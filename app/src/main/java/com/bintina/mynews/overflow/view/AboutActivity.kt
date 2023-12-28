package com.bintina.mynews.overflow

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.bintina.mynews.databinding.ActivityAboutBinding

class AboutActivity: AppCompatActivity() {
    lateinit var binding : ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}