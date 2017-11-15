package com.devknightzzz.newsup

/**
 * @author vinayagasundar
 */
class ApiResponse<out R>(val status: Boolean, val message: String, val data: R?) {

    companion object {
        private val SUCCESS_MESSAGE = "success"
        private val ERROR_MESSAGE = "error"

        fun <R : Any> success(data: R, message: String = SUCCESS_MESSAGE): ApiResponse<R> {
            return ApiResponse(true, message, data)
        }

        fun <R : Any> error(message: String = ERROR_MESSAGE): ApiResponse<R> {
            return ApiResponse(false, message, null)
        }
    }
}