package com.devknightzzz.newsup.database.local

import android.content.Context
import android.content.SharedPreferences
import com.devknightzzz.newsup.NewsAndroidAPI

/**
 * @author vinayagasundar
 */
class PrefUtils private constructor(private val context: Context) {


    private val prefs: SharedPreferences by lazy {
        val pref: SharedPreferences = context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE)
        pref
    }


    companion object {
        private val APP_PREFS = "com.devknightzzz.newsup.APPS_PREFS"

        val instance: PrefUtils by lazy {
            val prefUtils = PrefUtils(NewsAndroidAPI.instance.appContext)
            prefUtils
        }
    }


    fun putBoolean(key: String, value: Boolean) {
        prefs.edit()
                .putBoolean(key, value)
                .apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return prefs.getBoolean(key, defaultValue)
    }

    fun putLong(key: String, value: Long) {
        prefs.edit()
                .putLong(key, value)
                .apply()
    }

    fun getLong(key: String, defaultValue: Long = -1): Long {
        return prefs.getLong(key, defaultValue)
    }

}