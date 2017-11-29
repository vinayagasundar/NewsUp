package com.devknightzzz.newsup.database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.devknightzzz.newsup.database.convertor.SourceConverter

/**
 * @author vinayagasundar
 */
@Entity(tableName = "article")
@TypeConverters(SourceConverter::class)
data class Article(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                   val author: String,
                   val title: String,
                   val description: String,
                   val url: String,
                   val urlToImage: String,
                   val publishedAt: String,
                   val source: Pair<String, String>) {

    companion object {
        fun create(data: List<com.devknightzzz.newsup.model.Article>)
                : List<com.devknightzzz.newsup.database.entity.Article> {
            val articleList: MutableList<Article> = mutableListOf()
            data.forEach { article: com.devknightzzz.newsup.model.Article ->
                articleList.add(create(article))
            }
            return articleList
        }

        private fun create(data: com.devknightzzz.newsup.model.Article)
                : com.devknightzzz.newsup.database.entity.Article {
            return com.devknightzzz.newsup.database.entity.Article(0, data.author,
                    data.title, data.description, data.url, data.urlToImage,
                    data.publishedAt, data.source)
        }
    }
}