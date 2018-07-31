package ru.trubin23.tasksmvpkotlin.tasks

import ru.trubin23.tasksmvpkotlin.BasePresenter
import ru.trubin23.tasksmvpkotlin.BaseView
import ru.trubin23.tasksmvpkotlin.data.Task

interface TasksContract {

    interface View : BaseView<Presenter> {

        var isActive: Boolean

        fun showAddTask()

        fun showTaskDetail(taskId: String)

        fun showTaskMarkedComplete()

        fun showTaskMarkedActivate()

        fun showLoadingTasksError()

        fun showTasks(tasks: List<Task>)

        fun showActiveFilterLabel()

        fun showCompletedFilterLabel()

        fun showAllFilterLabel()

        fun showNoActiveTasks()

        fun showNoCompletedTasks()

        fun showNoTasks()

        fun showSuccessfullySavedMessage()

        fun setLoadingIndicator(active: Boolean)

        fun showCompletedTasksCleared()
    }

    interface Presenter : BasePresenter {

        var mCurrentFiltering: TasksFilterType

        fun addNewTask()

        fun openTaskDetail(requestedTaskId: String)

        fun completeTask(completeTaskId: String)

        fun activateTask(activateTaskId: String)

        fun clearCompletedTasks()

        fun loadTasks(forceUpdate: Boolean)

        fun result(requestCode: Int, resultCode: Int)
    }
}