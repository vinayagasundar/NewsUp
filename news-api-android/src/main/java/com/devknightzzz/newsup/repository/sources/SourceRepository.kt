package com.devknightzzz.newsup.repository.sources

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.devknightzzz.newsup.ApiResponse
import com.devknightzzz.newsup.INewsService
import com.devknightzzz.newsup.ISettingDataSource
import com.devknightzzz.newsup.ISourceDataSource
import com.devknightzzz.newsup.core.AppExecutors
import com.devknightzzz.newsup.database.SourceDao
import com.devknightzzz.newsup.database.entity.Source
import org.threeten.bp.Instant

/**
 * @author vinayagasundar
 */
class SourceRepository(private val newsService: INewsService,
                       private val sourceDao: SourceDao,
                       private val settings: ISettingDataSource,
                       private val appExecutors: AppExecutors) : ISourceDataSource {


    override fun getAllSources(): LiveData<ApiResponse<List<Source>>> {
        val response = MutableLiveData<ApiResponse<List<Source>>>()

        if (isAllSourceFromDb()) {
            appExecutors.diskIO.execute {
                val listResponse = sourceDao.getAllSource()
                if (listResponse.isNotEmpty()) {
                    response.postValue(ApiResponse.success(listResponse))
                } else {
                    response.postValue(ApiResponse.error())
                }
            }
        } else {
            appExecutors.networkIO.execute({
                val listResponse = newsService.getAllSources().data

                if (listResponse != null) {
                    val sourceList = Source.create(listResponse)

                    sourceDao.insert(sourceList)
                    settings.setSourceExpireAt()
                    response.postValue(ApiResponse.success(sourceList))
                } else {
                    response.postValue(ApiResponse.error())
                }
            })
        }


        return response
    }


    private fun isAllSourceFromDb(): Boolean = settings.getSourceExpireAt() - Instant.now().toEpochMilli() > 0
}