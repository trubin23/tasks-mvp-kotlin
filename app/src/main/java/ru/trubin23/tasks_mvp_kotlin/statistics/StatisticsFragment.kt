package ru.trubin23.tasks_mvp_kotlin.statistics

import android.support.v4.app.Fragment

class StatisticsFragment : Fragment(), StatisticsContract.View {

    override lateinit var mPresenter: StatisticsContract.Presenter
}