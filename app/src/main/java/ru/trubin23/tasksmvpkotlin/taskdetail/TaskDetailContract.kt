package ru.trubin23.tasksmvpkotlin.taskdetail

import ru.trubin23.tasksmvpkotlin.BasePresenter
import ru.trubin23.tasksmvpkotlin.BaseView
import ru.trubin23.tasksmvpkotlin.data.Task

interface TaskDetailContract {

    interface View : BaseView<Presenter> {
        fun showMissingTask()

        fun showTaskDelete()

        fun showLoadingIndicator()

        fun showTask(task: Task)

        fun showEditTask(taskId: String)
    }

    interface Presenter : BasePresenter {
        fun deleteTask()

        fun editTask()
    }
}