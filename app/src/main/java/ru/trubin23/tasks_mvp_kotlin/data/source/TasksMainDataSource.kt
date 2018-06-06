package ru.trubin23.tasks_mvp_kotlin.data.source

interface TasksMainDataSource : TasksDataSource {

    fun clearCompletedTasks()
}