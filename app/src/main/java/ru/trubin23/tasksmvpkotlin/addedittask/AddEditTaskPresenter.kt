package ru.trubin23.tasksmvpkotlin.addedittask

import ru.trubin23.tasksmvpkotlin.data.source.TasksRepository

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