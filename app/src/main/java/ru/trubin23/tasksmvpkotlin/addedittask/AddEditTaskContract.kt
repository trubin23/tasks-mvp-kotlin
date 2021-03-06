package ru.trubin23.tasksmvpkotlin.addedittask

import ru.trubin23.tasksmvpkotlin.BasePresenter
import ru.trubin23.tasksmvpkotlin.BaseView

interface AddEditTaskContract {

    interface View : BaseView<Presenter> {

        val isActive: Boolean

        fun setTitle(title: String)

        fun setDescription(description: String)

        fun showEmptyTaskError()

        fun showTaskList()
    }

    interface Presenter : BasePresenter {
        var isDataMissing: Boolean

        fun saveTask(title: String, description: String)
    }
}