package com.devknightzzz.newsup.network

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
}