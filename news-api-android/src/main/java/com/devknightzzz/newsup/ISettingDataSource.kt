package com.devknightzzz.newsup

import android.arch.lifecycle.LiveData
import com.devknightzzz.newsup.database.entity.Source
import org.threeten.bp.Instant

/**
 * @author vinayagasundar
 */
interface ISettingDataSource {

    fun isFirstLaunch(): Boolean

    fun setFirstLaunch(isFirst: Boolean = true)

    fun getSourceExpireAt(): Long

    fun setSourceExpireAt(timeInMills: Long = Instant.now().plusSeconds(86400).toEpochMilli())

    fun getSubscribedSource(): LiveData<ApiResponse<List<Source>>>

    fun setSubscribedSource(sourcesList: List<Source>)
}