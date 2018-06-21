package ru.trubin23.tasks_mvp_kotlin.statistics

import ru.trubin23.tasks_mvp_kotlin.BasePresenter
import ru.trubin23.tasks_mvp_kotlin.BaseView

interface StatisticsContract {

    interface View : BaseView<Presenter> {
    }

    interface Presenter : BasePresenter {
    }
}