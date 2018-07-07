package ru.trubin23.tasks_mvp_kotlin.addedittask

import ru.trubin23.tasks_mvp_kotlin.data.source.TasksRepository

class AddEditTaskPresenter(private val mTasksRepository: TasksRepository,
                           private val mTaskDetailView: AddEditTaskContract.View)
    : AddEditTaskContract.Presenter {
    override fun start() {
    }
}