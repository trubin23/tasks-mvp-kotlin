package ru.trubin23.tasksmvpkotlin.taskdetail

import ru.trubin23.tasksmvpkotlin.BasePresenter
import ru.trubin23.tasksmvpkotlin.BaseView

interface TaskDetailContract {

    interface View : BaseView<Presenter> {

        val isActive: Boolean

        fun showMissingTask()

        fun showTaskDelete()

        fun showLoadingIndicator()

        fun showTitle(title: String)

        fun showDescription(description: String)

        fun showCompletionStatus(complete: Boolean)

        fun showEditTask(taskId: String)

        fun showTaskMarkedComplete()

        fun showTaskMarkedActive()
    }

    interface Presenter : BasePresenter {
        fun deleteTask()

        fun editTask()

        fun completeTask()

        fun activateTask()
    }
}