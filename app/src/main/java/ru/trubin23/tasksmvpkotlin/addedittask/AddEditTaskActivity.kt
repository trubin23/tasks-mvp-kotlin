package ru.trubin23.tasksmvpkotlin.addedittask

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ru.trubin23.tasksmvpkotlin.R
import ru.trubin23.tasksmvpkotlin.util.Injection
import ru.trubin23.tasksmvpkotlin.util.addFragmentToActivity

class AddEditTaskActivity : AppCompatActivity() {

    private lateinit var mAddEditTaskPresenter: AddEditTaskPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addtask_act)

        val taskId = intent.getStringExtra(EXTRA_TASK_ID)

        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setTitle(if (taskId == null) R.string.add_task else R.string.edit_task)
        }

        val addEditTaskFragment = supportFragmentManager.findFragmentById(R.id.content_frame)
                as AddEditTaskFragment? ?: AddEditTaskFragment.newInstance().also {
            addFragmentToActivity(it, R.id.content_frame)
        }

        val shouldLoadDataFromRepo = savedInstanceState?.getBoolean(SHOULD_LOAD_DATA_FROM_REPO_KEY)
                ?: true

        mAddEditTaskPresenter = AddEditTaskPresenter(taskId,
                Injection.provideTasksRepository(applicationContext),
                addEditTaskFragment, shouldLoadDataFromRepo)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState?.apply {
            putBoolean(SHOULD_LOAD_DATA_FROM_REPO_KEY, mAddEditTaskPresenter.isDataMissing)
        })
    }

    companion object {
        const val EXTRA_TASK_ID = "ADD_EDIT_TASK_ID"
        const val SHOULD_LOAD_DATA_FROM_REPO_KEY = "SHOULD_LOAD_DATA_FROM_REPO_KEY"

        const val REQUEST_ADD_TASK = 1
        const val REQUEST_EDIT_TASK = 2
    }
}
