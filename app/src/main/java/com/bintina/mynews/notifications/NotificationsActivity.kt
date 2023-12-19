package com.bintina.mynews.notifications

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.bintina.mynews.databinding.ActivityNotificationsBinding

class NotificationsActivity: AppCompatActivity() {
    lateinit var binding: ActivityNotificationsBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}