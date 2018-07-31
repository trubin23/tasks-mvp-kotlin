package ru.trubin23.tasksmvpkotlin.tasks.tasklist

interface TaskItemListener {

    fun onTaskClick(clickedTaskId: String)

    fun onCompleteTask(completeTaskId: String)

    fun onActivateTask(activateTaskId: String)
}