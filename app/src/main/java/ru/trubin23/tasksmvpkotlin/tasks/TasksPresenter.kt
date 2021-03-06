package ru.trubin23.tasksmvpkotlin.tasks

import android.app.Activity
import ru.trubin23.tasksmvpkotlin.addedittask.AddEditTaskActivity
import ru.trubin23.tasksmvpkotlin.data.Task
import ru.trubin23.tasksmvpkotlin.data.source.TasksDataSource
import ru.trubin23.tasksmvpkotlin.data.source.TasksRepository

class TasksPresenter(private val mTasksRepository: TasksRepository,
                     private val mTasksView: TasksContract.View)
    : TasksContract.Presenter {

    override var mCurrentFiltering = TasksFilterType.ALL_TASKS

    private var mFirstLoad = true

    init {
        mTasksView.mPresenter = this
    }

    override fun start() {
        loadTasks(true)
    }

    override fun loadTasks(forceUpdate: Boolean) {
        loadTasks(forceUpdate || mFirstLoad, true)
        mFirstLoad = false
    }

    private fun loadTasks(forceUpdate: Boolean, showLoadingUI: Boolean) {
        if (showLoadingUI) {
            mTasksView.setLoadingIndicator(true)
        }
        if (forceUpdate) {
            mTasksRepository.refreshTasks()
        }

        mTasksRepository.getTasks(object : TasksDataSource.LoadTasksCallback {
            override fun onTasksLoaded(tasks: List<Task>) {
                val tasksToShow = ArrayList<Task>()

                for (task in tasks) {
                    when (mCurrentFiltering) {
                        TasksFilterType.ALL_TASKS -> tasksToShow.add(task)
                        TasksFilterType.ACTIVE_TASKS -> if (task.isActive) {
                            tasksToShow.add(task)
                        }
                        TasksFilterType.COMPLETED_TASKS -> if (task.mIsCompleted) {
                            tasksToShow.add(task)
                        }
                    }
                }

                if (!mTasksView.isActive) {
                    return
                }

                if (showLoadingUI) {
                    mTasksView.setLoadingIndicator(false)
                }
                showTasks(tasksToShow)
            }

            override fun onDataNotAvailable() {
                if (!mTasksView.isActive) {
                    return
                }

                if (showLoadingUI) {
                    mTasksView.setLoadingIndicator(false)
                }
                mTasksView.showLoadingTasksError()
            }
        })
    }

    private fun showTasks(tasks: List<Task>) {
        if (tasks.isEmpty()) {
            showEmptyTasks()
        } else {
            mTasksView.showTasks(tasks)
            showFilteringLabel()
        }
    }

    private fun showEmptyTasks() {
        when (mCurrentFiltering) {
            TasksFilterType.ACTIVE_TASKS -> mTasksView.showNoActiveTasks()
            TasksFilterType.COMPLETED_TASKS -> mTasksView.showNoCompletedTasks()
            else -> mTasksView.showNoTasks()
        }
    }

    private fun showFilteringLabel() {
        when (mCurrentFiltering) {
            TasksFilterType.ACTIVE_TASKS -> mTasksView.showActiveFilterLabel()
            TasksFilterType.COMPLETED_TASKS -> mTasksView.showCompletedFilterLabel()
            else -> mTasksView.showAllFilterLabel()
        }
    }

    override fun result(requestCode: Int, resultCode: Int) {
        if (AddEditTaskActivity.REQUEST_ADD_TASK == requestCode &&
                Activity.RESULT_OK == resultCode) {
            mTasksView.showSuccessfullySavedMessage()
        }
    }

    override fun addNewTask() {
        mTasksView.showAddTask()
    }

    override fun openTaskDetail(requestedTaskId: String) {
        mTasksView.showTaskDetail(requestedTaskId)
    }

    override fun completeTask(completeTaskId: String) {
        mTasksRepository.completedTask(completeTaskId, true)
        mTasksView.showTaskMarkedComplete()
        loadTasks(false, false)
    }

    override fun activateTask(activateTaskId: String) {
        mTasksRepository.completedTask(activateTaskId, false)
        mTasksView.showTaskMarkedActivate()
        loadTasks(false, false)
    }

    override fun clearCompletedTasks() {
        mTasksRepository.clearCompletedTasks()
        mTasksView.showCompletedTasksCleared()
        loadTasks(false, false)
    }
}
