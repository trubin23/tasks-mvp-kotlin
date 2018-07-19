package ru.trubin23.tasksmvpkotlin.taskdetail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.*
import android.widget.CheckBox
import android.widget.TextView
import ru.trubin23.tasksmvpkotlin.R
import ru.trubin23.tasksmvpkotlin.addedittask.AddEditTaskActivity
import ru.trubin23.tasksmvpkotlin.addedittask.AddEditTaskActivity.Companion.REQUEST_EDIT_TASK
import ru.trubin23.tasksmvpkotlin.data.Task
import ru.trubin23.tasksmvpkotlin.util.showSnackBar

class TaskDetailFragment : Fragment(), TaskDetailContract.View {

    private lateinit var mComplete: CheckBox
    private lateinit var mTitle: TextView
    private lateinit var mDescription: TextView

    override lateinit var mPresenter: TaskDetailContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.taskdetail_frag, container, false)
        with(root) {
            mComplete = findViewById(R.id.task_detail_complete)
            mTitle = findViewById(R.id.task_detail_title)
            mDescription = findViewById(R.id.task_detail_description)
        }

        activity?.findViewById<FloatingActionButton>(R.id.fab_edit_task)
                ?.setOnClickListener { mPresenter.editTask() }

        return root
    }

    override fun onResume() {
        super.onResume()
        mPresenter.start()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
        inflater.inflate(R.menu.taskdetail_frag_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val deletePressed = item.itemId == R.id.menu_delete
        if (deletePressed) {
            mPresenter.deleteTask()
        }
        return deletePressed
    }

    override fun showTitle(title: String) {
        mTitle.text = title
    }

    override fun showDescription(description: String) {
        mDescription.text = description
    }

    override fun showCompletionStatus(complete: Boolean) {
        with(mComplete){
            isChecked = complete
            setOnCheckedChangeListener{ _, isCheckedNow  ->
                if (isCheckedNow){
                    mPresenter.completeTask()
                } else{
                    mPresenter.activateTask()
                }
            }
        }
    }

    override fun showLoadingIndicator() {
        mTitle.text = ""
        mDescription.text = getString(R.string.loading)
    }

    override fun showMissingTask() {
        mTitle.text = ""
        mDescription.text = getString(R.string.no_data)
    }

    override fun showTaskDelete() {
        activity?.finish()
    }

    override fun showEditTask(taskId: String) {
        val intent = Intent(context, AddEditTaskActivity::class.java)
        intent.putExtra(AddEditTaskActivity.EXTRA_TASK_ID, taskId)
        startActivityForResult(intent, REQUEST_EDIT_TASK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_EDIT_TASK) {
            if (resultCode == Activity.RESULT_OK) {
                activity?.finish()
            }
        }
    }

    override fun showTaskMarkedComplete() {
        view?.showSnackBar(getString(R.string.task_marked_complete), Snackbar.LENGTH_LONG)
    }

    override fun showTaskMarkedActive() {
        view?.showSnackBar(getString(R.string.task_marked_active), Snackbar.LENGTH_LONG)
    }

    companion object {
        fun newInstance() = TaskDetailFragment()
    }
}