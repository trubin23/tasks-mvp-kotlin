package ru.trubin23.tasks_mvp_kotlin.util

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

const val NETWORK_THREAD_COUNT = 3

open class AppExecutors constructor(
        val diskIO: Executor = DiskIOThreadExecutor(),
        val networkIO: Executor = Executors.newFixedThreadPool(NETWORK_THREAD_COUNT),
        val mainThread: Executor = MainThreadExecutor()
) {

    private class MainThreadExecutor : Executor {

        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}