package ru.trubin23.tasksmvpkotlin.data.source.cache

import ru.trubin23.tasksmvpkotlin.data.Task

interface TasksCacheDataSource {

    fun getTasks(): List<Task>?

    fun setTasks(tasks: List<Task>)

    fun getTaskById(taskId: String): Task?

    fun addTask(task: Task)

    fun removeTask(taskId: String)

    fun completedTask(taskId: String, completed: Boolean)

    fun clearCompletedTasks()

    fun irrelevantState()
}