package com.example.datasaving

import android.content.Context

class Repository(
    private val dataSource: DataSource,
    private val now: Now
) {
    private var time: Long = 0

    fun time(): Long {
        return dataSource.getLong(KEY, 0)
    }


    fun startTracking() {
        time = now.time()
    }

    fun stopTracking() {
        val now = now.time()
        val difference = now - time
        val saved = time()
        val new = saved + difference
        dataSource.putLong(KEY, new)
    }

    companion object {
        private const val KEY = "time_key"
    }
}

interface Now {
    fun time(): Long

    class Base : Now {
        override fun time(): Long = System.currentTimeMillis()
    }

}

interface DataSource {

    fun putLong(key: String, value: Long)

    fun getLong(key: String, default: Long): Long

    class Base(context: Context) : DataSource {
        private val sharedPreferences =
            context.getSharedPreferences("main", Context.MODE_PRIVATE)

        override fun putLong(key: String, value: Long) {

            sharedPreferences.edit().putLong(key, value).apply()
        }

        override fun getLong(key: String, default: Long): Long {
            return sharedPreferences.getLong(key, default)
        }
    }
}