package ru.trubin23.tasksmvpkotlin.taskdetail

import ru.trubin23.tasksmvpkotlin.data.source.TasksRepository

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
        if (mTaskId.isEmpty()){
            mTaskDetailView.showMissingTask()
            return
        }
        mTasksRepository.deleteTask(mTaskId)
        mTaskDetailView.showTaskDelete()
    }
}