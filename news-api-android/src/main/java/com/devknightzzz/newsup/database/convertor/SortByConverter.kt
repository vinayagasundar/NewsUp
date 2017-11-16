package com.devknightzzz.newsup.database.convertor

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author
 */
class SortByConverter {

    private val gson: Gson by lazy {
        val gson = Gson()
        gson
    }

    @TypeConverter
    fun toJSON(value: List<String>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun fromJSON(value: String): List<String> {
        val type = object: TypeToken<List<String>>(){}.type
        return gson.fromJson(value, type)
    }
}