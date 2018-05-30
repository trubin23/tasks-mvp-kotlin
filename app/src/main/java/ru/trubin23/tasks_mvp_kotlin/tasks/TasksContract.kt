package ru.trubin23.tasks_mvp_kotlin.tasks

import ru.trubin23.tasks_mvp_kotlin.BasePresenter
import ru.trubin23.tasks_mvp_kotlin.BaseView
import ru.trubin23.tasks_mvp_kotlin.data.Task

interface TasksContract {

    interface View : BaseView<Presenter> {

        fun showAddTask()

        fun showTaskDetail(mId: String)

        fun showTaskMarkedComplete()

        fun showTaskMarkedActivate()

        fun showLoadingTasksError()

        fun showTasks(tasks: List<Task>)
    }

    interface Presenter : BasePresenter {

        fun addNewTask()

        fun openTaskDetail(requestedTask: Task)

        fun completeTask(completeTask: Task)

        fun activateTask(activateTask: Task)
    }
}