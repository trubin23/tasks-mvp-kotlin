package ru.trubin23.tasksmvpkotlin.taskdetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.CheckBox
import android.widget.TextView
import ru.trubin23.tasksmvpkotlin.R
import ru.trubin23.tasksmvpkotlin.data.Task

class TaskDetailFragment : Fragment(), TaskDetailContract.View {

    private lateinit var mComplete: CheckBox
    private lateinit var mTitle: TextView
    private lateinit var mDescription: TextView

    override lateinit var mPresenter: TaskDetailContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.taskdetail_frag, container, false)
        with(root){
            mComplete = findViewById(R.id.task_detail_complete)
            mTitle = findViewById(R.id.task_detail_title)
            mDescription = findViewById(R.id.task_detail_description)
        }
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
        if (deletePressed){
            mPresenter.deleteTask()
        }
        return deletePressed
    }

    override fun showTask(task: Task) {
        mComplete.isChecked = task.mIsCompleted
        mTitle.text = task.mTitle
        mDescription.text = task.mDescription
    }

    override fun showLoadingIndicator() {
        mTitle.text = ""
        mDescription.text = getString(R.string.no_data)
    }

    override fun showMissingTask() {
        mTitle.text = ""
        mDescription.text = getString(R.string.no_data)
    }

    override fun showTaskDelete() {
        activity?.finish()
    }

    companion object {
        fun newInstance() = TaskDetailFragment()
    }
}