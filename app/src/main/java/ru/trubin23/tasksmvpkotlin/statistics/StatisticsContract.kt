package ru.trubin23.tasksmvpkotlin.statistics

import ru.trubin23.tasksmvpkotlin.BasePresenter
import ru.trubin23.tasksmvpkotlin.BaseView

interface StatisticsContract {

    interface View : BaseView<Presenter> {
    }

    interface Presenter : BasePresenter {
    }
}