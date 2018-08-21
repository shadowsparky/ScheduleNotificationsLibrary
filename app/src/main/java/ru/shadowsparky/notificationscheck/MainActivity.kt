package ru.shadowsparky.notificationscheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*
import kotlin.math.min

class MainActivity : AppCompatActivity() {
    private var scheduler : NotificationScheduler? = null
    private var refreshButton : Button? = null
    private var deleteButton : Button? = null
    private var day : EditText? = null
    private var hour : EditText? = null
    private var minutes : EditText? = null
    private var id : EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LogUtils.print("Application Started")
        varsInit()
        controlsInit()
        listenersInit()
    }

    fun varsInit() {
        scheduler = NotificationScheduler(this)
    }

    fun controlsInit() {
        refreshButton = findViewById(R.id.refresh)
        deleteButton = findViewById(R.id.delete)
        day = findViewById(R.id.day)
        hour = findViewById(R.id.hour)
        minutes = findViewById(R.id.minutes)
        id = findViewById(R.id.id)
    }

    fun listenersInit() {
        refreshButton?.setOnClickListener {
            val day = this.day?.text.toString().toInt()
            val hour = this.hour?.text.toString().toInt()
            val minutes = this.minutes?.text.toString().toInt()
            val id = this.id?.text.toString().toInt()
            val calendar = scheduler?.getCalendar(2018, 8, day, hour, minutes)
            scheduler?.scheduleNotification("Title", "Message", calendar!!, id)
            Toast.makeText(this, "Запланировано", Toast.LENGTH_SHORT).show()
        }
        deleteButton?.setOnClickListener{
            val id = this.id?.text.toString().toInt()
            scheduler?.removeSchedule(id)
            Toast.makeText(this, "Планирование отменено", Toast.LENGTH_SHORT).show()
        }
    }
}