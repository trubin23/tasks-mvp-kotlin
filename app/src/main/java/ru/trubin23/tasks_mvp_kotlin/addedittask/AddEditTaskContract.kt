package ru.trubin23.tasks_mvp_kotlin.addedittask

import ru.trubin23.tasks_mvp_kotlin.BasePresenter
import ru.trubin23.tasks_mvp_kotlin.BaseView

interface AddEditTaskContract {

    interface View : BaseView<Presenter> {
    }

    interface Presenter : BasePresenter {
    }
}