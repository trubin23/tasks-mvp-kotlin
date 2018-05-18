package ru.trubin23.tasks_mvp_kotlin.tasks

import ru.trubin23.tasks_mvp_kotlin.BasePresenter
import ru.trubin23.tasks_mvp_kotlin.BaseView

interface TasksContract {

    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter {

        fun addNewTask()
    }
}