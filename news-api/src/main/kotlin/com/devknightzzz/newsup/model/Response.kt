package com.devknightzzz.newsup.model

/**
 * @author vinayagasundar
 */
open class Response(private val status: String?) {
    open fun isValid(): Boolean {
        return status.equals("ok")
    }
}