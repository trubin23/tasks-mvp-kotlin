package ru.trubin23.tasks_mvp_kotlin.taskdetail

import ru.trubin23.tasks_mvp_kotlin.data.source.TasksRepository

class TaskDetailPresenter(
        private val mTaskId: String,
        private val mTasksRepository: TasksRepository,
        private val mTaskDetailView: TaskDetailContract.View)
    : TaskDetailContract.Presenter {

    init {
        mTaskDetailView.mPresenter = this
    }

    override fun start() {
    }

    override fun deleteTask() {
    }
}