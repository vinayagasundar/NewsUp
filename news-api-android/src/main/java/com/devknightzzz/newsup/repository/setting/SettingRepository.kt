package com.devknightzzz.newsup.repository.setting

import com.devknightzzz.newsup.ISettingDataSource
import com.devknightzzz.newsup.core.AppExecutors
import com.devknightzzz.newsup.database.local.PrefUtils

/**
 * @author vinayagasundar
 */
class SettingRepository : ISettingDataSource {

    companion object {
        private val KEY_IS_FIRST_LAUNCH = "isFirstLaunch"
    }

    override fun isFirstLaunch(): Boolean {
        return PrefUtils.instance.getBoolean(KEY_IS_FIRST_LAUNCH, true)
    }

    override fun setFirstLaunch(isFirst: Boolean) {
        AppExecutors.instance.diskIO.execute {
            PrefUtils.instance.putBoolean(KEY_IS_FIRST_LAUNCH, isFirst)
        }
    }
}