package ru.trubin23.tasksmvpkotlin.tasks.task_list

import ru.trubin23.tasksmvpkotlin.data.Task

interface TaskItemListener {

    fun onTaskClick(clickedTask: Task)

    fun onCompleteTask(completeTask: Task)

    fun onActivateTask(activateTask: Task)
}