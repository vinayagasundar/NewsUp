package com.devknightzzz.newsup.repository.setting

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
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

    override fun isFirstLaunch(): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()
        AppExecutors.instance.diskIO.execute {
            val isFirstLaunch = PrefUtils.instance.getBoolean(KEY_IS_FIRST_LAUNCH)
            data.postValue(isFirstLaunch)
        }
        return data
    }

    override fun setFirstLaunch(isFirst: Boolean) {
        AppExecutors.instance.diskIO.execute {
            PrefUtils.instance.putBoolean(KEY_IS_FIRST_LAUNCH, isFirst)
        }
    }
}