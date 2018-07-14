package ru.trubin23.tasksmvpkotlin.data.source

interface TasksMainDataSource : TasksDataSource {

    fun refreshTasks()
}