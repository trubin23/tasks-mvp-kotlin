package ru.trubin23.tasks_mvp_kotlin.tasks

import ru.trubin23.tasks_mvp_kotlin.BasePresenter
import ru.trubin23.tasks_mvp_kotlin.BaseView
import ru.trubin23.tasks_mvp_kotlin.data.Task

interface TasksContract {

    interface View : BaseView<Presenter> {

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

        fun setLoadingIndicator(active: Boolean)

        fun showCompletedTasksCleared()
    }

    interface Presenter : BasePresenter {

        var mCurrentFiltering : TasksFilterType

        fun addNewTask()

        fun openTaskDetail(requestedTask: Task)

        fun completeTask(completeTask: Task)

        fun activateTask(activateTask: Task)

        fun clearCompletedTasks()

        fun loadTasks(forceUpdate: Boolean)
    }
}