package com.devknightzzz.newsup.repository.articles

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.devknightzzz.newsup.ApiResponse
import com.devknightzzz.newsup.IArticleDataSource
import com.devknightzzz.newsup.INewsService
import com.devknightzzz.newsup.core.AppExecutors
import com.devknightzzz.newsup.database.entity.Article

/**
 * @author vinayagasundar
 */
class ArticleRepository(private val newsService: INewsService,
                        private val appExecutors: AppExecutors): IArticleDataSource {




    override fun getArticles(sources: String?,
                             query: String?,
                             category: String?,
                             language: String?): LiveData<ApiResponse<List<Article>>> {


        val response = MutableLiveData<ApiResponse<List<Article>>>()
        response.postValue(ApiResponse.loading())

        appExecutors.networkIO.execute {
            val listResponse = newsService.getArticles(sources, query, category, language).data
            if (listResponse != null) {
                val articleList = Article.create(listResponse)
                response.postValue(ApiResponse.success(articleList))
            } else {
                response.postValue(ApiResponse.error())
            }
        }


        return response
    }
}