package com.bintina.mynews.notifications.controller.work

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.bintina.mynews.R
import com.bintina.mynews.notifications.view.NotificationDisplayActivity
import com.bintina.mynews.notifications.controller.NotificationsActivity
import com.bintina.mynews.common.util.Constants
import com.bintina.mynews.common.util.Constants.CHANNEL_ID
import com.bintina.mynews.common.util.MyApp.Companion.FILTERS
import com.bintina.mynews.common.util.MyApp.Companion.QUERY_TERM
import com.bintina.mynews.common.util.MyApp.Companion.notificationBooleanArts
import com.bintina.mynews.common.util.MyApp.Companion.notificationKeyword
import com.bintina.mynews.common.util.preferenceToString

/**
 * Worker class responsible for handling background work related to notifications.
 */
class NotificationWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    /**
     * Executes the background work for the notification.
     */
    override fun doWork(): Result {
        // Create notification channel
        createNotificationChannel()

        // Set notification channel and ID
        val notificationChannelId = Constants.CHANNEL_ID
        val notificationId = Constants.NOTIFICATION_ID

        //Fetch notification settings
        val query = preferenceToString(applicationContext, QUERY_TERM)
        val filters = preferenceToString(applicationContext, FILTERS)

        //Handle notification Click
        val mainIntent = Intent(applicationContext, NotificationDisplayActivity::class.java)
        mainIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        val mainPendingIntent = PendingIntent.getActivity(
            applicationContext, 1, mainIntent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        // Set up second button intent
        val secondBtnIntent = Intent(applicationContext, DismissNotificationReceiver::class.java)
        val secondBtPendingIntent = PendingIntent.getActivity(
            applicationContext, 2, secondBtnIntent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )


        // Build the notification
        val notificationBuilder =
            NotificationCompat.Builder(applicationContext, notificationChannelId)
        notificationBuilder.setSmallIcon(R.drawable.small_notifications_active_24)
        notificationBuilder.setContentTitle(Constants.NOTIFICATION_TITLE)
        notificationBuilder.priority = NotificationCompat.PRIORITY_DEFAULT
        //Set cancel
        notificationBuilder.setAutoCancel(true)
        //add click intent
        notificationBuilder.setContentIntent(mainPendingIntent)
        //add second button
        notificationBuilder.addAction(
            R.drawable.baseline_cancel_15,
            "Dismiss",
            secondBtPendingIntent
        )

        // Notify using NotificationManagerCompat
        val notificationManagerCompat = NotificationManagerCompat.from(applicationContext)
        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return ListenableWorker.Result.success()
        }
        notificationManagerCompat.notify(notificationId, notificationBuilder.build())

        // Indicate whether the work finished successfully with the Result
        Log.d("WorkerSuccessLog", "Worker reached success() return")
        return Result.success()
    }


    /**
     * Creates a notification channel for Android Oreo (API level 26) and above.
     */
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = Constants.NOTIFICATION_CHANNEL_NAME
            val description = Constants.NOTIFICATION_CHANNEL_DESCRIPTION
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val notificationChannel = NotificationChannel(CHANNEL_ID, name, importance)
            notificationChannel.description = description

            val notificationManager =
                applicationContext.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)


        }
    }
}

