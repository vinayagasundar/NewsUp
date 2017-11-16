package com.devknightzzz.newsup.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.devknightzzz.newsup.NewsAndroidAPI
import com.devknightzzz.newsup.database.entity.Source

/**
 * @author vinayagasundar
 */
@Database(entities = arrayOf(Source::class), version = 1)
abstract class AppDB: RoomDatabase() {

    companion object {
        val instance: AppDB by lazy {
            val appDb: AppDB = Room.databaseBuilder(NewsAndroidAPI.instance.appContext,
                AppDB::class.java, "newsup.db").build()
            appDb
        }
    }

    abstract fun sourceDao(): SourceDao
}