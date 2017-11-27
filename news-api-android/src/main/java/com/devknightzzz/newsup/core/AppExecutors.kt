package com.devknightzzz.newsup.core

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * @author vinayagasundar
 */
class AppExecutors(val diskIO: Executor = Executors.newSingleThreadExecutor(),
                   val networkIO: Executor = Executors.newFixedThreadPool(3),
                   val mainThread: Executor = MainThreadExecutor()) {

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