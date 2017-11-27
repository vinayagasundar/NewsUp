package com.devknightzzz.newsup

import android.annotation.SuppressLint
import android.content.Context
import com.devknightzzz.newsup.core.AppExecutors
import com.devknightzzz.newsup.database.AppDB
import com.devknightzzz.newsup.database.local.PrefUtils
import com.devknightzzz.newsup.repository.SourceRepository
import com.devknightzzz.newsup.repository.setting.SettingRepository
import com.jakewharton.threetenabp.AndroidThreeTen

/**
 * @author vinayagasundar
 */
@SuppressLint("StaticFieldLeak")
class NewsAndroidAPI private constructor(context: Context) {

    val appContext: Context = context

    companion object {
        lateinit var instance: NewsAndroidAPI

        fun init(context: Context) {
            instance = NewsAndroidAPI(context.applicationContext)
            AndroidThreeTen.init(context)
        }
    }


    fun getSourceDataSource(): ISourceDataSource = SourceRepository(NewsApi.newsService,
            AppDB.instance.sourceDao(),
            getSettingDataSource(),
            AppExecutors.instance)

    fun getSettingDataSource(): ISettingDataSource = SettingRepository(PrefUtils.instance)
}