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

    private lateinit var mShowTasksView: View
    private lateinit var mFilteringLabelView: TextView

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
            mShowTasksView = findViewById(R.id.show_tasks)
            val listView = findViewById<ListView>(R.id.tasks_list).apply {
                adapter = mListAdapter
            }
            mFilteringLabelView = findViewById(R.id.filtering_label)

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
        mListAdapter.mTasks = tasks
        mShowTasksView.visibility = View.VISIBLE
        mNoTasksView.visibility = View.GONE
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

    override fun showActiveFilterLabel() {
        mFilteringLabelView.text = getString(R.string.label_active)
    }

    override fun showCompletedFilterLabel() {
        mFilteringLabelView.text = getString(R.string.label_completed)
    }

    override fun showAllFilterLabel() {
        mFilteringLabelView.text = getString(R.string.label_all)
    }

    private fun showMessage(message: String) {
        view?.showSnackBar(message, Snackbar.LENGTH_LONG)
    }

    override fun showNoActiveTasks() {
        showNoTasksViews(resources.getString(R.string.no_tasks_active),
                R.drawable.ic_verified)
    }

    override fun showNoCompletedTasks() {
        showNoTasksViews(resources.getString(R.string.no_tasks_completed),
                R.drawable.ic_check_box)
    }

    override fun showNoTasks() {
        showNoTasksViews(resources.getString(R.string.no_tasks_all),
                R.drawable.ic_check_circle)
    }

    private fun showNoTasksViews(descriptionText: String?, iconRes: Int) {
        mShowTasksView.visibility = View.GONE
        mNoTasksView.visibility = View.VISIBLE

        mNoTasksLabel.text = descriptionText
        mNoTasksIcon.setImageResource(iconRes)
    }

    companion object {
        fun newInstance() = TasksFragment()
    }
}