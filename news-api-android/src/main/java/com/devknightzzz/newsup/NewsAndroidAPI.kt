package com.devknightzzz.newsup

import android.annotation.SuppressLint
import android.content.Context
import com.devknightzzz.newsup.repository.SourceRepository
import com.devknightzzz.newsup.repository.setting.SettingRepository

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
        }
    }


    fun getSourceDataSource(): ISourceDataSource = SourceRepository()

    fun getSettingDataSource(): ISettingDataSource = SettingRepository()
}