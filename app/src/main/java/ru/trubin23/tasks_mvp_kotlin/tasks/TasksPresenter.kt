package ru.trubin23.tasks_mvp_kotlin.tasks

import ru.trubin23.tasks_mvp_kotlin.data.Task
import ru.trubin23.tasks_mvp_kotlin.data.source.TasksDataSource
import ru.trubin23.tasks_mvp_kotlin.data.source.TasksRepository

class TasksPresenter(private val mTasksRepository: TasksRepository,
                     private val mTasksView: TasksContract.View)
    : TasksContract.Presenter {

    private var mCurrentFiltering = TasksFilterType.ALL_TASKS

    private var mFirstLoad = true

    init {
        mTasksView.mPresenter = this
    }

    override fun start() {
        loadTasks(true)
    }

    override fun loadTasks(forceUpdate: Boolean) {
        loadTasks(true, true)
    }

    private fun loadTasks(forceUpdate: Boolean, showLoadingUI: Boolean) {
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

                showTasks(tasksToShow)
            }

            override fun onDataNotAvailable() {
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

    override fun clearCompletedTasks() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
