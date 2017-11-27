package com.devknightzzz.newsup

import org.threeten.bp.Instant

/**
 * @author vinayagasundar
 */
interface ISettingDataSource {

    fun isFirstLaunch(): Boolean

    fun setFirstLaunch(isFirst: Boolean = true)

    fun getSourceExpireAt(): Long

    fun setSourceExpireAt(timeInMills: Long = Instant.now().plusSeconds(86400).toEpochMilli())
}