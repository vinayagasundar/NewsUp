package com.devknightzzz.newsup

/**
 * @author vinayagasundar
 */
class ApiResponse<out R>(val status: Status, val message: String, val data: R?) {

    companion object {
        private val SUCCESS_MESSAGE = "success"
        private val ERROR_MESSAGE = "error"
        private val LOADING_MESSAGE = "loading"

        fun <R : Any> success(data: R, message: String = SUCCESS_MESSAGE): ApiResponse<R> {
            return ApiResponse(Status.SUCCESS, message, data)
        }

        fun <R : Any> error(message: String = ERROR_MESSAGE): ApiResponse<R> {
            return ApiResponse(Status.ERROR, message, null)
        }

        fun <R: Any> loading(message: String = LOADING_MESSAGE): ApiResponse<R> {
            return ApiResponse(Status.LOADING, message, null)
        }
    }
}