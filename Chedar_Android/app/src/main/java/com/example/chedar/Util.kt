package com.example.chedar

import android.annotation.SuppressLint
import com.example.chedar.database.Zombie
import java.text.SimpleDateFormat

/**
 * Take the Long milliseconds returned by the system and stored in Room,
 * and convert it to a nicely formatted string for display.
 *
 * EEEE - Display the long letter version of the weekday
 * MMM - Display the letter abbreviation of the nmotny
 * dd-yyyy - day in month and full year numerically
 * HH:mm - Hours and minutes in 24hr format
 */
@SuppressLint("SimpleDateFormat")
fun convertLongToDateString(systemTime: Long): String {
    return SimpleDateFormat("EEEE MMM-dd-yyyy' Time: 'HH:mm")
        .format(systemTime).toString()
}

fun getZombieLevel(zombie: Zombie): Int {
    var level: Int = 0

    if (zombie.birthday) {
        level += 1
    }
    if (zombie.address) {
        level += 3
    }
    if (zombie.card) {
        level += 3
    }

    return level
}