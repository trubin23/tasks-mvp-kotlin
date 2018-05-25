package ru.trubin23.tasks_mvp_kotlin.data.source.local

import ru.trubin23.tasks_mvp_kotlin.data.source.TasksDataSource

interface TasksLocalDataSource : TasksDataSource {

    fun deleteAllTasks()
}