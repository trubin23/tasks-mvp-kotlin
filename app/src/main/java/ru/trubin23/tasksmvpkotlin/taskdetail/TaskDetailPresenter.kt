package ru.trubin23.tasksmvpkotlin.taskdetail

import ru.trubin23.tasksmvpkotlin.data.Task
import ru.trubin23.tasksmvpkotlin.data.source.TasksDataSource
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
        if (mTaskId.isEmpty()) {
            mTaskDetailView.showMissingTask()
            return
        }

        mTaskDetailView.showLoadingIndicator()
        mTasksRepository.getTask(mTaskId, object : TasksDataSource.GetTaskCallback {
            override fun onTaskLoaded(task: Task) {
                if (!mTaskDetailView.isActive) {
                    return
                }
                showTask(task)
            }

            override fun onDataNotAvailable() {
                with(mTaskDetailView) {
                    if (!isActive) {
                        return@onDataNotAvailable
                    }
                    showMissingTask()
                }
            }
        })
    }

    override fun deleteTask() {
        if (mTaskId.isEmpty()) {
            mTaskDetailView.showMissingTask()
            return
        }
        mTasksRepository.deleteTask(mTaskId)
        mTaskDetailView.showTaskDelete()
    }

    override fun editTask() {
        if (mTaskId.isEmpty()) {
            mTaskDetailView.showMissingTask()
            return
        }
        mTaskDetailView.showEditTask(mTaskId)
    }

    override fun completeTask() {
        if (mTaskId.isEmpty()) {
            mTaskDetailView.showMissingTask()
            return
        }
        mTasksRepository.completedTask(mTaskId, true)
        mTaskDetailView.showTaskMarkedComplete()
    }

    override fun activateTask() {
        if (mTaskId.isEmpty()) {
            mTaskDetailView.showMissingTask()
            return
        }
        mTasksRepository.completedTask(mTaskId, false)
        mTaskDetailView.showTaskMarkedActive()
    }

    private fun showTask(task: Task) {
        if (mTaskId == task.mId && mTaskId.isNotEmpty()) {
            with(mTaskDetailView) {
                showTitle(task.mTitle)
                showDescription(task.mDescription)
                showCompletionStatus(task.mIsCompleted)
            }
        }
    }
}