package ru.trubin23.tasks_mvp_kotlin.addedittask

import ru.trubin23.tasks_mvp_kotlin.data.source.TasksRepository

class AddEditTaskPresenter(
        private val mTaskId: String,
        private val mTasksRepository: TasksRepository,
        private val mAddEditTaskView: AddEditTaskContract.View)
    : AddEditTaskContract.Presenter {

    init {
        mAddEditTaskView.mPresenter = this
    }

    override fun start() {
    }
}