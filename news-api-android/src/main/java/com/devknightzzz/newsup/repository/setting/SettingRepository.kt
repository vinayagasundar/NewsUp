package com.devknightzzz.newsup.repository.setting

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.devknightzzz.newsup.ApiResponse
import com.devknightzzz.newsup.ISettingDataSource
import com.devknightzzz.newsup.core.AppExecutors
import com.devknightzzz.newsup.database.entity.Source
import com.devknightzzz.newsup.database.local.PrefUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.threeten.bp.Instant

/**
 * @author vinayagasundar
 */
class SettingRepository(private val prefs: PrefUtils,
                        private val appExecutors: AppExecutors,
                        private val gson: Gson = Gson()) : ISettingDataSource {

    companion object {
        private val KEY_IS_FIRST_LAUNCH = "isFirstLaunch"
        private val KEY_SOURCE_EXPIRES_AT = "sourceExpiresAt"
        private val KEY_SELECTED_SOURCES = "selectedSources"
    }

    override fun isFirstLaunch(): Boolean {
        return prefs.getBoolean(KEY_IS_FIRST_LAUNCH, true)
    }

    override fun setFirstLaunch(isFirst: Boolean) {
        appExecutors.diskIO.execute {
            prefs.putBoolean(KEY_IS_FIRST_LAUNCH, isFirst)
        }
    }

    override fun getSourceExpireAt(): Long {
        return prefs.getLong(KEY_SOURCE_EXPIRES_AT, Instant.now().toEpochMilli())
    }

    override fun setSourceExpireAt(timeInMills: Long) {
        appExecutors.diskIO.execute {
            prefs.putLong(KEY_SOURCE_EXPIRES_AT, timeInMills)
        }
    }

    override fun getSubscribedSource(): LiveData<ApiResponse<List<Source>>> {
        val sourceListResponse: MutableLiveData<ApiResponse<List<Source>>> = MutableLiveData()
        sourceListResponse.value = ApiResponse.loading()

        appExecutors.diskIO.execute {
            val data = prefs.getString(KEY_SELECTED_SOURCES, null)
            if (data != null) {
                val typeToken = object: TypeToken<List<Source>>(){}.type
                val sourceList = gson.fromJson<List<Source>>(data, typeToken)
                sourceList?.let {
                    sourceListResponse.postValue(ApiResponse.success(sourceList))
                }
            } else {
                sourceListResponse.postValue(ApiResponse.error())
            }
        }

        return sourceListResponse
    }

    override fun setSubscribedSource(sourcesList: List<Source>) {
        appExecutors.diskIO.execute {
            val data = gson.toJson(sourcesList)
            prefs.putString(KEY_SELECTED_SOURCES, data)
        }
    }
}