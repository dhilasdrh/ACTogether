package com.dhilasdrh.zerowaste.utils

import android.util.Patterns
import java.text.SimpleDateFormat
import java.util.*

object Commons {

    fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun getCurrentDateTime(format: String = Constants.DATE_TIME_FORMAT): String {
        return SimpleDateFormat(format, Locale.ROOT).format(Date())
    }

    fun formatTimeStampToText(
        timeStamp: String,
        timeStampFormat: String = Constants.DATE_TIME_FORMAT,
        format: String = Constants.DATE_TIME_STRING_FORMAT
    ): String {
        val dateTime = SimpleDateFormat(timeStampFormat, Locale.ROOT).parse(timeStamp) as Date
        return SimpleDateFormat(format, Locale.ROOT).format(dateTime)
    }
}