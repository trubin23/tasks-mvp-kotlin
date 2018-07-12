package ru.trubin23.tasks_mvp_kotlin.addedittask

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.trubin23.tasks_mvp_kotlin.R

class AddEditTaskFragment : Fragment(), AddEditTaskContract.View {

    override lateinit var mPresenter: AddEditTaskContract.Presenter

    private lateinit var mTitle: TextView
    private lateinit var mDescription: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.addtask_frag, container, false)
        with(root){
            mTitle = findViewById(R.id.add_task_title)
            mDescription = findViewById(R.id.add_task_description)
        }
        return root
    }

    override fun onResume() {
        super.onResume()
        mPresenter.start()
    }

    companion object {
        fun newInstance() = AddEditTaskFragment()
    }
}