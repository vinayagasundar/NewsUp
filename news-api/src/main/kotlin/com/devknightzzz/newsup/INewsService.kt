package com.devknightzzz.newsup

import com.devknightzzz.newsup.model.Source

/**
 * @author vinayagasundar
 */
interface INewsService {

    fun getAllSources(): ApiResponse<List<Source>>
}