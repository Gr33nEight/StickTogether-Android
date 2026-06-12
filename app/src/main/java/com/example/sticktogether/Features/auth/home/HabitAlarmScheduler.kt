package com.example.sticktogether.Features.auth.home


import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun scheduleHabitAlarm(context: Context, habitName: String, date: LocalDate, timeString: String) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, HabitAlarmReceiver::class.java).apply {
        putExtra("HABIT_NAME", habitName)
    }

    val pendingIntent = PendingIntent.getBroadcast(
        context,
        habitName.hashCode(),
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)
    val localTime = LocalTime.parse(timeString, timeFormatter)
    val localDateTime = LocalDateTime.of(date, localTime)
    val triggerTime = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

    try {
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            triggerTime,
            pendingIntent
        )
    } catch (e: SecurityException) {
        e.printStackTrace()
    }
}