package com.devknightzzz.newsup

import com.devknightzzz.newsup.service.NewsService

/**
 * @author vinayagasundar
 */
class NewsApi {
    companion object {
        val newsService: INewsService by lazy {
            val service = NewsService()
            service
        }
    }
}