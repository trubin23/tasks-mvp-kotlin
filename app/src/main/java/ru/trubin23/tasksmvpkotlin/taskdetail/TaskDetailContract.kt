package ru.trubin23.tasksmvpkotlin.taskdetail

import ru.trubin23.tasksmvpkotlin.BasePresenter
import ru.trubin23.tasksmvpkotlin.BaseView

interface TaskDetailContract {

    interface View : BaseView<Presenter> {
        fun showMissingTask()

        fun showTaskDelete()
    }

    interface Presenter : BasePresenter {
        fun deleteTask()
    }
}