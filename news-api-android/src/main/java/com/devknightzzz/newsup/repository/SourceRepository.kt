package com.devknightzzz.newsup.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.devknightzzz.newsup.ApiResponse
import com.devknightzzz.newsup.INewsService
import com.devknightzzz.newsup.ISourceDataSource
import com.devknightzzz.newsup.NewsApi
import com.devknightzzz.newsup.core.AppExecutors
import com.devknightzzz.newsup.database.entity.Source
import com.google.gson.Gson

/**
 * @author vinayagasundar
 */
class SourceRepository : ISourceDataSource {

    private val newsService: INewsService = NewsApi.newsService


    override fun getAllSources(): LiveData<ApiResponse<List<Source>>> {
        val response = MutableLiveData<ApiResponse<List<Source>>>()
        AppExecutors.instance.networkIO.execute({
            val sourcesListResponse = newsService.getAllSources().data


            Log.i("Hello", Gson().toJson(sourcesListResponse))

            if (sourcesListResponse != null) {
                response.postValue(ApiResponse.success(Source.create(sourcesListResponse)))
            } else {
                response.postValue(ApiResponse.error())
            }
        })
        return response
    }
}