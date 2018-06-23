package ru.trubin23.tasks_mvp_kotlin.taskdetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.trubin23.tasks_mvp_kotlin.R

class TaskDetailFragment : Fragment(), TaskDetailContract.View {

    override lateinit var mPresenter: TaskDetailContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.taskdetail_frag, container, false)
    }

    companion object {
        fun newInstance() = TaskDetailFragment()
    }
}