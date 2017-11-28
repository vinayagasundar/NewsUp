package com.devknightzzz.newsup.network

import com.devknightzzz.newsup.model.ArticleListResponse
import com.devknightzzz.newsup.model.SourceListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author vinayagasundar
 */
interface NewsOrgAPI {

    @GET("sources")
    fun getAllSources(@Query("apiKey") apiKey: String): Call<SourceListResponse>

    @GET("top-headlines")
    fun getArticles(@Query("apiKey") apiKey: String,
                    @Query("sources") sources: String? = null,
                    @Query("q") query: String? = null,
                    @Query("category") category: String? = null,
                    @Query("language") language: String? = null): Call<ArticleListResponse>
}