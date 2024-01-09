package com.bintina.mynews.notifications.controller.work


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat
import com.bintina.mynews.common.util.Constants

/**
 * Worker class responsible for dismissing notifications.
 */
class DismissNotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        // Dismiss the notification
        val notificationId = Constants.NOTIFICATION_ID
        val notificationManagerCompat = NotificationManagerCompat.from(context!!)
        notificationManagerCompat.cancel(notificationId)
    }
}