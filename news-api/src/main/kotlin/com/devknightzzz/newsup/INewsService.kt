package com.devknightzzz.newsup

import com.devknightzzz.newsup.model.Article
import com.devknightzzz.newsup.model.Source

/**
 * @author vinayagasundar
 */
interface INewsService {

    fun getAllSources(): ApiResponse<List<Source>>

    fun getArticles(sources: String? = null,
                    query: String? = null,
                    category: String? = null,
                    language: String? = null): ApiResponse<List<Article>>
}