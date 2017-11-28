package com.devknightzzz.newsup.ui.sources.selection

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.devknightzzz.newsup.ApiResponse
import com.devknightzzz.newsup.ISettingDataSource
import com.devknightzzz.newsup.ISourceDataSource
import com.devknightzzz.newsup.NewsAndroidAPI
import com.devknightzzz.newsup.database.entity.Source

/**
 * @author vinayagasundar
 */
class SourceSelectionViewModel: ViewModel() {

    private val sourceDataSource: ISourceDataSource = NewsAndroidAPI.instance.getSourceDataSource()
    private val settingDataSource: ISettingDataSource = NewsAndroidAPI.instance.getSettingDataSource()


    fun getAllSources(): LiveData<ApiResponse<List<Source>>> {
        return sourceDataSource.getAllSources()
    }

    fun addSelectedSource(sourceList: List<Source>) {
        settingDataSource.setSubscribedSource(sourceList)
    }
}