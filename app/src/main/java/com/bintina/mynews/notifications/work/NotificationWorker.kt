package com.bintina.mynews.notifications.work

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import androidx.work.ListenableWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.bintina.mynews.MainActivity
import com.bintina.mynews.R
import com.bintina.mynews.notifications.controller.NotificationsActivity
import com.bintina.mynews.notifications.controller.NotificationsDisplayFragment
import com.bintina.mynews.util.Constants
import com.bintina.mynews.util.Constants.CHANNEL_ID
import com.bintina.mynews.util.Constants.NOTIFICATION_ID
import java.util.concurrent.TimeUnit
import javax.sql.DataSource

class NotificationWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {


    override fun doWork(): Result {
        println("Hello from Worker!")
        // Do the work here--in this case, upload the images.
        //showNotification()
        Log.d("ShowNotificationLog", "showNotification() Triggered")
        createNotificationChannel()

        val notificationChannelId = Constants.CHANNEL_ID
        val notificationId = Constants.NOTIFICATION_ID

        //Handle notification Click
        val mainIntent = Intent(applicationContext, NotificationsDisplayFragment::class.java)
        //to pass data in notification and get it in Activity
//        mainIntent.putExtra("KEY_NAME","KEY_VALUE")

        mainIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        val mainPendingIntent = PendingIntent.getActivity(
            applicationContext, 1, mainIntent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        //secondBtnIntent....
        val secondBtnIntent = Intent(applicationContext, NotificationsActivity::class.java)
        //to pass data in notification and get it in Activity
        //        secondBtnIntent.putExtra("KE_NAME","KEY_VALUE")
        val secondBtPendingIntent = PendingIntent.getActivity(
            applicationContext, 2, secondBtnIntent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )
        //thirdBtnIntent....
        val thirdBtnIntent = Intent(applicationContext, NotificationsActivity::class.java)
        //to pass data in notification and get it in Activity
        //        thirdBtnIntent.putExtra("KE_NAME","KEY_VALUE")
        val thirdBtPendingIntent = PendingIntent.getActivity(
            applicationContext, 3, thirdBtnIntent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

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

        val notificationManagerCompat = NotificationManagerCompat.from(applicationContext)
        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return ListenableWorker.Result.success()
        }
        notificationManagerCompat.notify(notificationId, notificationBuilder.build())

        // Indicate whether the work finished successfully with the Result
        Log.d("WorkerSuccessLog", "Worker reached success() return")
        return Result.success()
    }

    fun showNotification() {

    }

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

