package com.devknightzzz.newsup.database.convertor

import android.arch.persistence.room.TypeConverter
import org.json.JSONObject

/**
 * @author vinayagasundar
 */
class SourceConverter {

    @TypeConverter
    fun toJSON(value: Pair<String, String>): String {
        val jsonObject = JSONObject()
        jsonObject.put(value.first, value.second)
        return jsonObject.toString()
    }

    @TypeConverter
    fun fromJSON(value: String): Pair<String, String> {
        val jsonObject = JSONObject(value)
        val keyValue = jsonObject.keys().next()
        val jsonValue = jsonObject.optString(keyValue)
        return Pair(keyValue, jsonValue)
    }

}