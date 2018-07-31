package ru.trubin23.tasksmvpkotlin.statistics

import ru.trubin23.tasksmvpkotlin.BasePresenter
import ru.trubin23.tasksmvpkotlin.BaseView

interface StatisticsContract {

    interface View : BaseView<Presenter> {

        val isActive: Boolean

        fun setProgressIndicator(active: Boolean)

        fun showStatistics(numberOfActiveTasks: Int, numberOfCompletedTasks: Int)

        fun showLoadingStatisticsError()
    }

    interface Presenter : BasePresenter
}