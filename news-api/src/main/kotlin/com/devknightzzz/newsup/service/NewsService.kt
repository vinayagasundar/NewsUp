package com.devknightzzz.newsup.service

import com.devknightzzz.newsup.ApiResponse
import com.devknightzzz.newsup.INewsService
import com.devknightzzz.newsup.model.Source
import com.devknightzzz.newsup.model.SourceListResponse
import com.devknightzzz.newsup.network.NewsOrgAPI
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author vinayagasundar
 */
class NewsService : INewsService {

    private val apiSource: NewsOrgAPI by lazy {

        val okHttp = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor())
                .build()

        val gson = GsonBuilder().setLenient().create()

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://newsapi.org/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttp)
                .build()
        retrofit.create(NewsOrgAPI::class.java)
    }


    override fun getAllSources(): ApiResponse<List<Source>> {

//        val callback: Callback<SourceListResponse> = object: Callback<SourceListResponse> {
//            override fun onResponse(call: Call<SourceListResponse>?, response: Response<SourceListResponse>?) {
//
//            }
//
//            override fun onFailure(call: Call<SourceListResponse>?, t: Throwable?) {
//
//            }
//        }
//
//        apiSource.getAllSources().enqueue(callback)


        val sourceListResponse = apiSource.getAllSources().execute()

        if (sourceListResponse.isSuccessful) {
            val sourceList = sourceListResponse.body()

            if (sourceList?.isValid() != null && sourceList.sources?.isNotEmpty() != null) {
                return ApiResponse.success(sourceList.sources)
            }
        }

        return ApiResponse.error()
    }
}