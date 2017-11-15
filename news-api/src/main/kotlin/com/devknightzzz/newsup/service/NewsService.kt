package com.devknightzzz.newsup.service

import com.devknightzzz.newsup.ApiResponse
import com.devknightzzz.newsup.INewsService
import com.devknightzzz.newsup.model.Source
import com.devknightzzz.newsup.network.NewsOrgAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

/**
 * @author vinayagasundar
 */
class NewsService : INewsService {

    private val apiSource: NewsOrgAPI by lazy {

        val okHttp = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor())
                .build()

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://newsapi.org/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttp)
                .build()
        retrofit.create(NewsOrgAPI::class.java)
    }


    override fun getAllSources(): ApiResponse<List<Source>> {
        val fileInputStream = this::class.java.classLoader.getResourceAsStream("keys.properties")
        val props = Properties()
        props.load(fileInputStream)
        val keys = props.getProperty("news.api.key")


        val sourceListResponse = apiSource.getAllSources(keys).execute()

        if (sourceListResponse.isSuccessful) {
            val sourceList = sourceListResponse.body()

            if (sourceList?.isValid() != null && sourceList.sources?.isNotEmpty() != null) {
                return ApiResponse.success(sourceList.sources)
            }
        }

        return ApiResponse.error()
    }
}