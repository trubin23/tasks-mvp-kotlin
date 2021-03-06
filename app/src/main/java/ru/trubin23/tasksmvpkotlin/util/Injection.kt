package ru.trubin23.tasksmvpkotlin.util

import android.content.Context
import ru.trubin23.tasksmvpkotlin.data.source.TasksRepository
import ru.trubin23.tasksmvpkotlin.data.source.cache.TasksCacheRepository
import ru.trubin23.tasksmvpkotlin.data.source.local.TasksDatabase
import ru.trubin23.tasksmvpkotlin.data.source.local.TasksLocalRepository
import ru.trubin23.tasksmvpkotlin.data.source.remote.TasksRemoteRepository

object Injection {
    fun provideTasksRepository(context: Context): TasksRepository {
        val database = TasksDatabase.getInstance(context)

        val appExecutors = AppExecutors()

        return TasksRepository.getInstance(
                TasksRemoteRepository.getInstance(appExecutors),
                TasksLocalRepository.getInstance(appExecutors, database.tasksDao()),
                TasksCacheRepository.getInstance())
    }
}