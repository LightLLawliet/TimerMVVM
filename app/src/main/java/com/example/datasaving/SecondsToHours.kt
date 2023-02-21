package com.example.datasaving

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

interface SecondsToHours {

    fun map(value: Long): String

    class Base : SecondsToHours {
        private val simpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale.ENGLISH)

        override fun map(value: Long): String {
            val date = Date(value)
            return simpleDateFormat.format(date)
        }
    }
}