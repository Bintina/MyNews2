package com.bintina.mynews.notifications

import android.app.ForegroundServiceStartNotAllowedException
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import androidx.core.content.ContextCompat
import com.bintina.mynews.Manifest
import com.bintina.mynews.R

class NotificationService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }



        private fun startForegroundNotification() {

            try {
                val notification = NotificationCompat.Builder(this, "CHANNEL_ID")
                    val builder = buildBuilder()
                    // Create the notification to display while the service is running
                    builder.build()
                ServiceCompat.startForeground(
                    /* service = */ this,
                    /* id = */ 100, // Cannot be 0
                    /* notification = */ notification,
                    /* foregroundServiceType = */
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
                    } else {
                        0
                    },
                )
            } catch (e: Exception) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
                    && e is ForegroundServiceStartNotAllowedException
                ) {
                    // App not in a valid state to start foreground service
                    // (e.g. started from bg)
                }
                // ...
            }
        }
fun buildBuilder(){
    val CHANEL_ID = createNotificationChannel()
    val builder = NotificationCompat.Builder(this, CHANEL_ID)
        .setSmallIcon(androidx.core.R.drawable.notification_icon_background)
        .setContentTitle(getString(R.string.notification_title))
        .setContent(getString(R.string.notification_text_content))
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    return builder
}
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val CHANNEL_ID = 101.toString()
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system.
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}