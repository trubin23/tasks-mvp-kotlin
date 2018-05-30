package ru.trubin23.tasks_mvp_kotlin.tasks

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import ru.trubin23.tasks_mvp_kotlin.R
import ru.trubin23.tasks_mvp_kotlin.data.Task
import ru.trubin23.tasks_mvp_kotlin.tasks.task_list.TaskItemListener
import ru.trubin23.tasks_mvp_kotlin.tasks.task_list.TasksAdapter
import ru.trubin23.tasks_mvp_kotlin.util.showSnackBar

class TasksFragment : Fragment(), TasksContract.View {

    override lateinit var mPresenter: TasksContract.Presenter

    private lateinit var mNoTasksView: View
    private lateinit var mNoTasksIcon: ImageView
    private lateinit var mNoTasksLabel: TextView

    private val mItemListener: TaskItemListener = object : TaskItemListener {
        override fun onTaskClick(clickedTask: Task) {
            mPresenter.openTaskDetail(clickedTask)
        }

        override fun onCompleteTask(completeTask: Task) {
            mPresenter.completeTask(completeTask)
        }

        override fun onActivateTask(activateTask: Task) {
            mPresenter.activateTask(activateTask)
        }
    }

    private val mListAdapter = TasksAdapter(mItemListener)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.tasks_frag, container, false)

        with(root) {

            val listView = findViewById<ListView>(R.id.tasks_list).apply { adapter = mListAdapter }

            mNoTasksView = findViewById(R.id.no_tasks)
            mNoTasksIcon = findViewById(R.id.no_tasks_icon)
            mNoTasksLabel = findViewById(R.id.no_tasks_label)
        }

        activity?.findViewById<FloatingActionButton>(R.id.fab_add_task)?.setOnClickListener { mPresenter.addNewTask() }

        return root
    }

    override fun onResume() {
        super.onResume()
        mPresenter.start()
    }

    override fun showTasks(tasks: List<Task>) {

    }

    override fun showAddTask() {
    }

    override fun showTaskDetail(mId: String) {
    }

    override fun showTaskMarkedComplete() {
        showMessage(getString(R.string.task_marked_complete))
    }

    override fun showTaskMarkedActivate() {
        showMessage(getString(R.string.task_marked_active))
    }

    override fun showLoadingTasksError() {
        showMessage(getString(R.string.loading_tasks_error))
    }

    private fun showMessage(message: String) {
        view?.showSnackBar(message, Snackbar.LENGTH_LONG)
    }

    companion object {
        fun newInstance() = TasksFragment()
    }
}