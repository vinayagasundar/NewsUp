package com.devknightzzz.newsup.model


/**
 * @author vinayagasundar
 */
data class Source(val id: String,
                  val name: String,
                  val description: String,
                  val url: String,
                  val category: String,
                  val language: String,
                  val country: String,
                  val urlsToLogs: LogoURL?,
                  val sortBysAvailable: List<String>)


