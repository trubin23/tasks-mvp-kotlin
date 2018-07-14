package ru.trubin23.tasksmvpkotlin.data.source.local

import ru.trubin23.tasksmvpkotlin.data.Task
import ru.trubin23.tasksmvpkotlin.data.source.TasksDataSource


interface TasksLocalDataSource : TasksDataSource {

    fun setTasks(tasks: List<Task>)

    fun deleteAllTasks()
}