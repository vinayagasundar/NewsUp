package com.devknightzzz.newsup.ui.landing.topheadlines

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.devknightzzz.newsup.ApiResponse
import com.devknightzzz.newsup.IArticleDataSource
import com.devknightzzz.newsup.NewsAndroidAPI
import com.devknightzzz.newsup.database.entity.Article

/**
 * @author vinayagasundar
 */
class TopHeadlineViewModel: ViewModel() {

    private val articleDataSource: IArticleDataSource = NewsAndroidAPI.instance.getArticleDataSource()

    private lateinit var topHeadLines: LiveData<ApiResponse<List<Article>>>

    fun getTopHeadlines(): LiveData<ApiResponse<List<Article>>> {
        topHeadLines = articleDataSource.getArticles()

        return topHeadLines
    }


    private fun loadTopHeadlinesForSubscription() {

    }
}