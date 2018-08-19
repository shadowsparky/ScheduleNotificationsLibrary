package ru.shadowsparky.notificationscheck

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationReciever : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val title = intent.getStringExtra("Title")
        val message = intent.getStringExtra("Message")
        val notificationWorker = NotificationWorker(context)
        notificationWorker.sendNotification(title, message)
    }
}