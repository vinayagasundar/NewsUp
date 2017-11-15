package com.devknightzzz.newsup.core

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * @author vinayagasundar
 */
class AppExecutors private constructor() {

    val diskIO: Executor
    val networkIO: Executor
    val mainThread: Executor

    init {
        diskIO = Executors.newSingleThreadExecutor()
        networkIO = Executors.newFixedThreadPool(3)
        mainThread = MainThreadExecutor()
    }

    companion object {
        val instance: AppExecutors by lazy {
            val executor = AppExecutors()
            executor
        }
    }

    class MainThreadExecutor : Executor {
        private val handler: Handler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable?) {
            handler.post(command)
        }

    }
}