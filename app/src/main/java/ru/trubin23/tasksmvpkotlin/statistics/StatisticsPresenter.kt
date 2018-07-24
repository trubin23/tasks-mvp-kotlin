package ru.trubin23.tasksmvpkotlin.statistics

import ru.trubin23.tasksmvpkotlin.data.Task
import ru.trubin23.tasksmvpkotlin.data.source.TasksDataSource
import ru.trubin23.tasksmvpkotlin.data.source.TasksRepository

class StatisticsPresenter(private val mTasksRepository: TasksRepository,
                          private val mStatisticsView: StatisticsContract.View)
    : StatisticsContract.Presenter {

    init {
        mStatisticsView.mPresenter = this
    }

    override fun start() {
        mStatisticsView.setProgressIndicator(true)

        mTasksRepository.getTasks(object : TasksDataSource.LoadTasksCallback {
            override fun onTasksLoaded(tasks: List<Task>) {
                val completedTasks = tasks.filter { it.mIsCompleted }.size
                val activeTasks = tasks.size - completedTasks

                mStatisticsView.setProgressIndicator(false)
                mStatisticsView.showStatistics(activeTasks, completedTasks)
            }

            override fun onDataNotAvailable() {
                mStatisticsView.setProgressIndicator(false)
                mStatisticsView.showLoadingStatisticsError()
            }
        })
    }
}