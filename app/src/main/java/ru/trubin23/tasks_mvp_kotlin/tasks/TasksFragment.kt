package ru.trubin23.tasks_mvp_kotlin.tasks

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ru.trubin23.tasks_mvp_kotlin.R

class TasksFragment : Fragment(), TasksContract.View {

    override lateinit var presenter: TasksContract.Presenter

    private lateinit var mNoTasksView: View
    private lateinit var mNoTasksIcon: ImageView
    private lateinit var mNoTasksLabel: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.tasks_frag, container, false)

        with(root){

            mNoTasksView = findViewById(R.id.no_tasks)
            mNoTasksIcon = findViewById(R.id.no_tasks_icon)
            mNoTasksLabel = findViewById(R.id.no_tasks_label)
        }

        activity?.findViewById<FloatingActionButton>(R.id.fab_add_task)?.
                setOnClickListener { presenter.addNewTask() }

        return root
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    companion object {
        fun newInstance() = TasksFragment()
    }
}