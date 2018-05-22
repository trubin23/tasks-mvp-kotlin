package ru.trubin23.tasks_mvp_kotlin.tasks

import ru.trubin23.tasks_mvp_kotlin.data.Task

class TasksPresenter(private val mTasksView: TasksContract.View) : TasksContract.Presenter {

    init {
        mTasksView.mPresenter = this
    }

    override fun start() {
    }

    override fun addNewTask() {
        mTasksView.showAddTask()
    }

    override fun openTaskDetail(requestedTask: Task) {
        mTasksView.showTaskDetail(requestedTask.mId)
    }

    override fun completeTask(completeTask: Task) {
        mTasksView.showTaskMarkedComplete()
    }

    override fun activateTask(activateTask: Task) {
        mTasksView.showTaskMarkedActivate()
    }
}
