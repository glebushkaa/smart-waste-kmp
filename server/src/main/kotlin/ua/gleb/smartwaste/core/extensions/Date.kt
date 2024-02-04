package ua.gleb.smartwaste.core.extensions

import java.util.*
import java.util.concurrent.TimeUnit

fun calculateDaysSinceCreation(createdAt: String): Int {
    val createdDate = createdAt.toDate()
    val currentDate = Date()
    val diffInMillis = currentDate.time - createdDate.time
    return TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS).toInt()
}