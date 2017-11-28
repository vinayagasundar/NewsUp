package com.devknightzzz.newsup.model

/**
 * @author vinayagasundar
 */
class SourceListResponse(status: String?,
                         val sources: List<Source>?) : Response(status)
