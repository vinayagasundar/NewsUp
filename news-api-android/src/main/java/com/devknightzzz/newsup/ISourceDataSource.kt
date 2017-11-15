package com.devknightzzz.newsup

import android.arch.lifecycle.LiveData
import com.devknightzzz.newsup.database.entity.Source

/**
 * @author vinayagasundar
 */
interface ISourceDataSource {
    fun getAllSources(): LiveData<ApiResponse<List<Source>>>
}