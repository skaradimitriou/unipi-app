package com.stathis.unipiapp.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object SharedPrefsHelper {

    private val PREF_TIME = "Pref time"
    private lateinit var prefs: SharedPreferences

    fun setHelper(context: Context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun saveUpdateTime(time: Long) {
        prefs.edit().putLong(PREF_TIME, time).apply()
    }

    fun getUpdateTime(): Long {
        return prefs.getLong(PREF_TIME, 0)
    }

    fun getCacheDuration(): String? {
        return prefs.getString("pref_cache_duration", "")
    }
}