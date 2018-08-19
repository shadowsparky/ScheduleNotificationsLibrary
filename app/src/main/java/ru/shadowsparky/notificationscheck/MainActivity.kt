package ru.shadowsparky.notificationscheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.print("Application Started")
        setContentView(R.layout.activity_main)
        val scheduler = NotificationScheduler(this)
        val calendar = scheduler.getCalendar(2018, 8, 19, 23, 45)
        scheduler.scheduleNotification("test", "test", calendar, 0)
//        val calendar = scheduler.getCalendar(2018, 8, 20, 6, 33)
//        scheduler.scheduleNotification("Евгений,", "Доброе утро!", calendar, 1)
//        scheduler.removeSchedule(0)
    }
}