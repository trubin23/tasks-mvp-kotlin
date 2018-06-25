package ru.trubin23.tasks_mvp_kotlin.statistics

import ru.trubin23.tasks_mvp_kotlin.data.source.TasksRepository

class StatisticsPresenter(private val mTasksRepository: TasksRepository,
                          private val mStatisticsView: StatisticsContract.View)
    : StatisticsContract.Presenter {
    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}