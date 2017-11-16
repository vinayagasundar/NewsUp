package com.devknightzzz.newsup.database.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.devknightzzz.newsup.database.convertor.SortByConverter

/**
 * @author vinayagasundar
 */
@Entity(tableName = "source")
@TypeConverters(SortByConverter::class)
data class Source(@PrimaryKey var id: String,
                  var name: String,
                  var description: String,
                  var url: String,
                  var category: String,
                  var language: String,
                  var country: String,
                  @Embedded var urlsToLogs: LogoURL?,
                  var sortBysAvailable: List<String>) {


    companion object {
        fun create(data: List<com.devknightzzz.newsup.model.Source>)
                : List<com.devknightzzz.newsup.database.entity.Source> {
            val sourceList: MutableList<Source> = mutableListOf()
            data.forEach { source: com.devknightzzz.newsup.model.Source -> sourceList.add(create(source)) }
            return sourceList
        }


        private fun create(data: com.devknightzzz.newsup.model.Source)
                : com.devknightzzz.newsup.database.entity.Source {
            var logoUrl: com.devknightzzz.newsup.database.entity.LogoURL? = null

            data.urlsToLogs?.let {
                logoUrl = com.devknightzzz.newsup.database.entity.LogoURL(it.small,
                        it.medium, it.large)
            }

            return com.devknightzzz.newsup.database.entity.Source(data.id, data.name,
                    data.description, data.url, data.category, data.language, data.country, logoUrl,
                    data.sortBysAvailable)
        }
    }


}