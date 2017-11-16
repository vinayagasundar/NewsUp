package com.devknightzzz.newsup

/**
 * @author vinayagasundar
 */
interface ISettingDataSource {

    fun isFirstLaunch(): Boolean

    fun setFirstLaunch(isFirst: Boolean = true)
}