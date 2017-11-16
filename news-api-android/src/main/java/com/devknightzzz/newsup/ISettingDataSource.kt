package com.devknightzzz.newsup

import android.arch.lifecycle.LiveData

/**
 * @author vinayagasundar
 */
interface ISettingDataSource {

    fun isFirstLaunch(): LiveData<Boolean>

    fun setFirstLaunch(isFirst: Boolean = true)
}