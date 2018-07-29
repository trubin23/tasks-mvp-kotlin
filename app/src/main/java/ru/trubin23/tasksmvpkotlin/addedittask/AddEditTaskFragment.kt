package ru.trubin23.tasksmvpkotlin.addedittask

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.trubin23.tasksmvpkotlin.R
import ru.trubin23.tasksmvpkotlin.util.showSnackBar

class AddEditTaskFragment : Fragment(), AddEditTaskContract.View {

    override lateinit var mPresenter: AddEditTaskContract.Presenter

    override var isActive: Boolean = false
        get() = isAdded

    private lateinit var mTitle: TextView
    private lateinit var mDescription: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.addtask_frag, container, false)
        with(root) {
            mTitle = findViewById(R.id.add_task_title)
            mDescription = findViewById(R.id.add_task_description)
        }
        with(activity?.findViewById<FloatingActionButton>(R.id.fab_edit_task_done)) {
            this?.setImageResource(R.drawable.ic_done)
            this?.setOnClickListener {
                mPresenter.saveTask(mTitle.text.toString(), mDescription.text.toString())
            }
        }
        return root
    }

    override fun onResume() {
        super.onResume()
        mPresenter.start()
    }

    override fun setTitle(title: String) {
        mTitle.text = title
    }

    override fun setDescription(description: String) {
        mDescription.text = description
    }

    override fun showEmptyTaskError() {
        view?.showSnackBar(getString(R.string.empty_task_message), Snackbar.LENGTH_LONG)
    }

    override fun showTaskList() {
        with(activity) {
            this?.setResult(Activity.RESULT_OK)
            this?.finish()
        }
    }

    companion object {
        fun newInstance() = AddEditTaskFragment()
    }
}