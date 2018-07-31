package ru.trubin23.tasksmvpkotlin.statistics

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.trubin23.tasksmvpkotlin.R

class StatisticsFragment : Fragment(), StatisticsContract.View {

    private lateinit var mStatisticsTextView: TextView

    override lateinit var mPresenter: StatisticsContract.Presenter

    override var isActive: Boolean = false
        get() = isAdded

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.statistics_frag, container, false)
        mStatisticsTextView = root.findViewById(R.id.statistics)
        return root
    }

    override fun onResume() {
        super.onResume()
        mPresenter.start()
    }

    override fun setProgressIndicator(active: Boolean) {
        if (active) {
            mStatisticsTextView.text = getString(R.string.loading)
        } else {
            mStatisticsTextView.text = ""
        }
    }

    override fun showStatistics(numberOfActiveTasks: Int, numberOfCompletedTasks: Int) {
        if (numberOfActiveTasks == 0 && numberOfCompletedTasks == 0) {
            mStatisticsTextView.text = getString(R.string.statistics_no_tasks)
        } else {
            val displayString = "${getString(R.string.statistics_active_tasks)} " +
                    "$numberOfActiveTasks\n" +
                    "${getString(R.string.statistics_completed_tasks)} " +
                    "$numberOfCompletedTasks"
            mStatisticsTextView.text = displayString
        }
    }

    override fun showLoadingStatisticsError() {
        mStatisticsTextView.text = getString(R.string.statistics_error)
    }

    companion object {
        fun newInstance() = StatisticsFragment()
    }
}