package com.devknightzzz.newsup

import android.arch.lifecycle.LiveData
import com.devknightzzz.newsup.database.entity.Article

/**
 * @author vinayagasundar
 */
interface IArticleDataSource {

    fun getArticles(sources: String? = null,
                    query: String? = null,
                    category: String? = null,
                    language: String? = null): LiveData<ApiResponse<List<Article>>>
}