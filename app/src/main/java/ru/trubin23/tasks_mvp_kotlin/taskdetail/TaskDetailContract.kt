package ru.trubin23.tasks_mvp_kotlin.taskdetail

import ru.trubin23.tasks_mvp_kotlin.BasePresenter
import ru.trubin23.tasks_mvp_kotlin.BaseView

interface TaskDetailContract {

    interface View : BaseView<Presenter> {
    }

    interface Presenter : BasePresenter {
    }
}