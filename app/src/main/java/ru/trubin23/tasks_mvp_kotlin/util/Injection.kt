package ru.trubin23.tasks_mvp_kotlin.util

import android.content.Context
import ru.trubin23.tasks_mvp_kotlin.data.source.TasksRepository
import ru.trubin23.tasks_mvp_kotlin.data.source.local.TasksLocalRepository
import ru.trubin23.tasks_mvp_kotlin.data.source.remote.TasksRemoteRepository

object Injection {
    fun provideTasksRepository(context: Context): TasksRepository {
        return TasksRepository.getInstance(TasksRemoteRepository.getInstance(),
                TasksLocalRepository())
    }
}