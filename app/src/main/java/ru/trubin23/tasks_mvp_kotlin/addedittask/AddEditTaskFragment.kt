package ru.trubin23.tasks_mvp_kotlin.addedittask

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.trubin23.tasks_mvp_kotlin.R

class AddEditTaskFragment : Fragment(), AddEditTaskContract.View {

    override lateinit var mPresenter: AddEditTaskContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.addtask_frag, container, false)
    }
}