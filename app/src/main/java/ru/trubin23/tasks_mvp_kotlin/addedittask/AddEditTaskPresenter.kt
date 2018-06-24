package ru.trubin23.tasks_mvp_kotlin.addedittask

import ru.trubin23.tasks_mvp_kotlin.data.source.TasksRepository
import ru.trubin23.tasks_mvp_kotlin.taskdetail.TaskDetailContract

class AddEditTaskPresenter(private val mTasksRepository: TasksRepository,
                           private val mTaskDetailView: AddEditTaskContract.View)
    : AddEditTaskContract.Presenter {
    override fun start() {
    }
}