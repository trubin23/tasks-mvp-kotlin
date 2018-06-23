package ru.trubin23.tasks_mvp_kotlin.taskdetail

import ru.trubin23.tasks_mvp_kotlin.data.source.TasksRepository

class TaskDetailPresenter(private val mTasksRepository: TasksRepository,
                          private val mTaskDetailView: TaskDetailContract.View)
    : TaskDetailContract.Presenter {

    override fun start() {
    }
}