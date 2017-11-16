package com.devknightzzz.newsup

import android.app.Application

/**
 * @author vinayagasundar
 */
class NewsUpApp: Application() {

    override fun onCreate() {
        super.onCreate()

        NewsAndroidAPI.init(this)
    }
}