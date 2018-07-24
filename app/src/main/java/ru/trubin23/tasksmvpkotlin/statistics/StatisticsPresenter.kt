package ru.trubin23.tasksmvpkotlin.statistics

import ru.trubin23.tasksmvpkotlin.data.source.TasksRepository

class StatisticsPresenter(private val mTasksRepository: TasksRepository,
                          private val mStatisticsView: StatisticsContract.View)
    : StatisticsContract.Presenter {

    init {
        mStatisticsView.mPresenter = this
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}