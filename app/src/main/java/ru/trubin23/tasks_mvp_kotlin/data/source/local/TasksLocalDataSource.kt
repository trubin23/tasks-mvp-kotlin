package ru.trubin23.tasks_mvp_kotlin.data.source.local

import ru.trubin23.tasks_mvp_kotlin.data.Task
import ru.trubin23.tasks_mvp_kotlin.data.source.TasksDataSource


interface TasksLocalDataSource : TasksDataSource {

    fun setTasks(tasks: List<Task>)

    fun deleteAllTasks()
}