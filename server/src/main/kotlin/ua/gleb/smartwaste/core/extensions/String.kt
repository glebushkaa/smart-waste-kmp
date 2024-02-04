package ua.gleb.smartwaste.core.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

fun String.toDate(): Date {
    val simpleDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    return simpleDateFormat.parse(this) ?: throw ParseException("Invalid date format", 0)
}