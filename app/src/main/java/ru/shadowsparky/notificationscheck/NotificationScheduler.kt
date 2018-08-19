package ru.shadowsparky.notificationscheck
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*
import java.util.Calendar.*

open class NotificationScheduler(val context : Context) {
    private val intent = Intent(context, NotificationReciever::class.java)
    private val manager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun scheduleNotification(title : String, message : String, calendar : Calendar, code : Int) {
        intent.putExtra("Title", title)
        intent.putExtra("Message", message)
        val pending_intent = PendingIntent.getBroadcast(context, code, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        manager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pending_intent)
        LogUtils.print("NOTIFICATION WITH ID $code SCHEDULED")
    }

    fun removeSchedule(code : Int) {
        val pending_intent = PendingIntent.getBroadcast(context, code, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        manager.cancel(pending_intent)
        LogUtils.print("SCHEDULED NOTIFICATION WITH ID $code DELETED")
    }

    fun getCalendar(year : Int, month : Int, day : Int, hour : Int, minute : Int) : Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(YEAR, year)
        calendar.set(MONTH, month - 1)
        calendar.set(DAY_OF_MONTH, day)
        calendar.set(HOUR_OF_DAY, hour)
        calendar.set(MINUTE, minute)
        calendar.set(SECOND, 0)
        calendar.set(MILLISECOND, 0)
        return calendar
    }
}