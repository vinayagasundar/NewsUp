package com.devknightzzz.newsup.repository.setting

import com.devknightzzz.newsup.ISettingDataSource
import com.devknightzzz.newsup.core.AppExecutors
import com.devknightzzz.newsup.database.local.PrefUtils
import org.threeten.bp.Instant

/**
 * @author vinayagasundar
 */
class SettingRepository(private val prefs: PrefUtils) : ISettingDataSource {

    companion object {
        private val KEY_IS_FIRST_LAUNCH = "isFirstLaunch"
        private val KEY_SOURCE_EXPIRES_AT = "sourceExpiresAt"
        private val KEY_SELECTED_SOURCES = "selectedSources"
    }

    override fun isFirstLaunch(): Boolean {
        return prefs.getBoolean(KEY_IS_FIRST_LAUNCH, true)
    }

    override fun setFirstLaunch(isFirst: Boolean) {
        AppExecutors.instance.diskIO.execute {
            prefs.putBoolean(KEY_IS_FIRST_LAUNCH, isFirst)
        }
    }

    override fun getSourceExpireAt(): Long {
        return prefs.getLong(KEY_SOURCE_EXPIRES_AT, Instant.now().toEpochMilli())
    }

    override fun setSourceExpireAt(timeInMills: Long) {
        AppExecutors.instance.diskIO.execute {
            prefs.putLong(KEY_SOURCE_EXPIRES_AT, timeInMills)
        }
    }
}