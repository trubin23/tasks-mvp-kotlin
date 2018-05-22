package ru.trubin23.tasks_mvp_kotlin.tasks.task_list

import ru.trubin23.tasks_mvp_kotlin.data.Task

interface TaskItemListener {

    fun onTaskClick(clickedTask: Task)

    fun onCompleteTask(completeTask: Task)

    fun onActivateTask(activateTask: Task)
}