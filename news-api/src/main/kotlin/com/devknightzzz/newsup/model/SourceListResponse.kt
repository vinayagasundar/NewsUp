package com.devknightzzz.newsup.model

/**
 * @author vinayagasundar
 */
class SourceListResponse(private val status: String?, val sources: List<Source>?) : Response() {

    override fun isValid(): Boolean {
        return status.equals("ok")
    }
}