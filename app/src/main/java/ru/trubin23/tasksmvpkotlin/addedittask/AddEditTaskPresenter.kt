package ru.trubin23.tasksmvpkotlin.addedittask

import ru.trubin23.tasksmvpkotlin.data.Task
import ru.trubin23.tasksmvpkotlin.data.source.TasksDataSource
import ru.trubin23.tasksmvpkotlin.data.source.TasksRepository

class AddEditTaskPresenter(
        private val mTaskId: String?,
        private val mTasksRepository: TasksRepository,
        private val mAddEditTaskView: AddEditTaskContract.View)
    : AddEditTaskContract.Presenter {

    init {
        mAddEditTaskView.mPresenter = this
    }

    override fun start() {
        mTaskId?.apply {
            mTasksRepository.getTask(this, object : TasksDataSource.GetTaskCallback {
                override fun onTaskLoaded(task: Task) {
                    mAddEditTaskView.setTitle(task.mTitle)
                    mAddEditTaskView.setDescription(task.mDescription)
                }

                override fun onDataNotAvailable() {
                    mAddEditTaskView.showEmptyTaskError()
                }
            })
        }
    }
}