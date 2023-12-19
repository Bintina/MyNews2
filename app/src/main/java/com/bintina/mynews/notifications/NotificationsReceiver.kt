package com.bintina.mynews.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class NotificationsReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("NotificationsReceiverLog", "Notifications Receiver Broadcast Recieved")
        showNotificationsNews()
    }

    private fun showNotificationsNews() {
        TODO("Not yet implemented")
    }

}
