package com.devknightzzz.newsup.service

import com.devknightzzz.newsup.ApiResponse
import com.devknightzzz.newsup.INewsService
import com.devknightzzz.newsup.model.Article
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

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttp = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
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


    override fun getArticles(sources: String?, query: String?,
                             category: String?, language: String?): ApiResponse<List<Article>> {
        val fileInputStream = this::class.java.classLoader.getResourceAsStream("keys.properties")
        val props = Properties()
        props.load(fileInputStream)
        val keys = props.getProperty("news.api.key")

        val articleListResponse = apiSource.getArticles(keys, sources, query, category, language)
                .execute()

        if (articleListResponse.isSuccessful) {
            val articleList = articleListResponse.body()

            if (articleList?.isValid() != null && articleList.articles?.isNotEmpty() != null) {
                return ApiResponse.success(articleList.articles)
            }
        }

        return ApiResponse.error()
    }
}