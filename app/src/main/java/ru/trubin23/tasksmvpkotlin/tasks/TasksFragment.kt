package ru.trubin23.tasksmvpkotlin.tasks

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.*
import android.widget.ImageView
import android.widget.ListView
import android.widget.PopupMenu
import android.widget.TextView
import ru.trubin23.tasksmvpkotlin.R
import ru.trubin23.tasksmvpkotlin.addedittask.AddEditTaskActivity
import ru.trubin23.tasksmvpkotlin.data.Task
import ru.trubin23.tasksmvpkotlin.taskdetail.TaskDetailActivity
import ru.trubin23.tasksmvpkotlin.tasks.tasklist.TaskItemListener
import ru.trubin23.tasksmvpkotlin.tasks.tasklist.TasksAdapter
import ru.trubin23.tasksmvpkotlin.util.showSnackBar

class TasksFragment : Fragment(), TasksContract.View {

    override lateinit var mPresenter: TasksContract.Presenter

    override var isActive: Boolean = false
        get() = isAdded

    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout

    private lateinit var mNoTasksView: View
    private lateinit var mNoTasksIcon: ImageView
    private lateinit var mNoTasksLabel: TextView

    private lateinit var mShowTasksView: View

    private lateinit var mFilteringLabelView: TextView

    private val mItemListener: TaskItemListener = object : TaskItemListener {
        override fun onTaskClick(clickedTaskId: String) {
            mPresenter.openTaskDetail(clickedTaskId)
        }

        override fun onCompleteTask(completeTaskId: String) {
            mPresenter.completeTask(completeTaskId)
        }

        override fun onActivateTask(activateTaskId: String) {
            mPresenter.activateTask(activateTaskId)
        }
    }

    private val mListAdapter = TasksAdapter(mItemListener)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.tasks_frag, container, false)

        with(root) {
            mSwipeRefreshLayout = findViewById(R.id.refresh_layout)
            mSwipeRefreshLayout.setOnRefreshListener { mPresenter.loadTasks(false) }

            mShowTasksView = findViewById(R.id.show_tasks)
            findViewById<ListView>(R.id.tasks_list).apply {
                adapter = mListAdapter
            }
            mFilteringLabelView = findViewById(R.id.filtering_label)

            mNoTasksView = findViewById(R.id.no_tasks)
            mNoTasksIcon = findViewById(R.id.no_tasks_icon)
            mNoTasksLabel = findViewById(R.id.no_tasks_label)
        }

        activity?.findViewById<FloatingActionButton>(R.id.fab_add_task)?.apply {
            setImageResource(R.drawable.ic_add)
            setOnClickListener { mPresenter.addNewTask() }
        }

        setHasOptionsMenu(true)

        return root
    }

    override fun onResume() {
        super.onResume()
        mPresenter.start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mPresenter.result(requestCode, resultCode)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
        inflater.inflate(R.menu.tasks_frag_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_filter -> showFilteringPopUpMenu()
            R.id.menu_clear -> mPresenter.clearCompletedTasks()
            R.id.menu_refresh -> mPresenter.loadTasks(true)
        }
        return true
    }

    private fun showFilteringPopUpMenu() {
        PopupMenu(context, activity?.findViewById(R.id.menu_filter)).apply {
            menuInflater.inflate(R.menu.filter_tasks, menu)
            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_active -> mPresenter.mCurrentFiltering = TasksFilterType.ACTIVE_TASKS
                    R.id.menu_completed -> mPresenter.mCurrentFiltering = TasksFilterType.COMPLETED_TASKS
                    else -> mPresenter.mCurrentFiltering = TasksFilterType.ALL_TASKS
                }
                mPresenter.loadTasks(false)
                true
            }
            show()
        }
    }

    override fun showTasks(tasks: List<Task>) {
        mListAdapter.mTasks = tasks
        mShowTasksView.visibility = View.VISIBLE
        mNoTasksView.visibility = View.GONE
    }

    override fun showAddTask() {
        val intent = Intent(context, AddEditTaskActivity::class.java)
        startActivityForResult(intent, AddEditTaskActivity.REQUEST_ADD_TASK)
    }

    override fun showTaskDetail(taskId: String) {
        val intent = Intent(context, TaskDetailActivity::class.java).apply {
            putExtra(TaskDetailActivity.EXTRA_TASK_ID, taskId)
        }
        startActivity(intent)
    }

    override fun setLoadingIndicator(active: Boolean) {
        with(mSwipeRefreshLayout) { post { isRefreshing = active } }
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

    override fun showCompletedTasksCleared() {
        showMessage(getString(R.string.completed_tasks_cleared))
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
        showNoTasksViews(getString(R.string.no_tasks_active),
                R.drawable.ic_verified)
    }

    override fun showNoCompletedTasks() {
        showNoTasksViews(getString(R.string.no_tasks_completed),
                R.drawable.ic_check_box)
    }

    override fun showNoTasks() {
        showNoTasksViews(getString(R.string.no_tasks_all),
                R.drawable.ic_check_circle)
    }

    override fun showSuccessfullySavedMessage() {
        showMessage(getString(R.string.task_saved_successfully))
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