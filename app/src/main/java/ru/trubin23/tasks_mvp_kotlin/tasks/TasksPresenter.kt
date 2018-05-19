package ru.trubin23.tasks_mvp_kotlin.tasks

class TasksPresenter(val tasksView: TasksContract.View)
    : TasksContract.Presenter {

    init {
        tasksView.mPresenter = this
    }

    override fun start() {
    }

    override fun addNewTask() {
    }
}