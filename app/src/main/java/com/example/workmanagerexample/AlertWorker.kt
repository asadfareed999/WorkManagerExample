package com.example.workmanagerexample

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters


class AlertWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    private val WORK_RESULT = "work_result"

    override fun doWork(): Result {
        showNotification(
            "WorkManager",
            "Shift has been Finished"
        )
        val outputData: Data = Data.Builder().putString(WORK_RESULT, "Shift Finished").build()
        return Result.success(outputData)
    }

    private fun showNotification(task: String, desc: String) {
        val manager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "task_channel"
        val channelName = "task_name"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val builder =
            NotificationCompat.Builder(applicationContext, channelId)
                .setContentTitle(task)
                .setContentText(desc)
                .setSmallIcon(R.drawable.ic_media_play)
        manager.notify(1, builder.build())
    }
}