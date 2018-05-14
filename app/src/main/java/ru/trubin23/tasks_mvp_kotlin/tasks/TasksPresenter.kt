package ru.trubin23.tasks_mvp_kotlin.tasks

class TasksPresenter(val tasksView: TasksContract.View)
    : TasksContract.Presenter {

    init {
        tasksView.presenter = this
    }

    override fun start() {
    }
}