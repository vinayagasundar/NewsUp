package com.devknightzzz.newsup.model

/**
 * @author vinayagasundar
 */
class ArticleListResponse(status: String?, val articles: List<Article>?): Response(status)