package ru.trubin23.tasksmvpkotlin.statistics

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.trubin23.tasksmvpkotlin.R

class StatisticsFragment : Fragment(), StatisticsContract.View {

    override lateinit var mPresenter: StatisticsContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.statistics_frag, container, false)
    }

    override fun setProgressIndicator(active: Boolean) {
    }

    override fun showStatistics(numberOfActiveTasks: Int, numberOfCompletedTasks: Int) {
    }

    override fun showLoadingStatisticsError() {
    }

    companion object {
        fun newInstance() = StatisticsFragment()
    }
}