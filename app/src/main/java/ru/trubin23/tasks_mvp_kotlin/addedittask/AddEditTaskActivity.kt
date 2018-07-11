package ru.trubin23.tasks_mvp_kotlin.addedittask

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ru.trubin23.tasks_mvp_kotlin.R
import ru.trubin23.tasks_mvp_kotlin.taskdetail.TaskDetailActivity
import ru.trubin23.tasks_mvp_kotlin.util.Injection
import ru.trubin23.tasks_mvp_kotlin.util.addFragmentToActivity

class AddEditTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addtask_act)

        val taskId = intent.getStringExtra(TaskDetailActivity.EXTRA_TASK_ID)

        val addEditTaskFragment = supportFragmentManager.findFragmentById(R.id.content_frame)
                as AddEditTaskFragment ? ?: AddEditTaskFragment.newInstance().also {
            addFragmentToActivity(it, R.id.content_frame)
        }

        AddEditTaskPresenter(taskId, Injection.provideTasksRepository(applicationContext),
                addEditTaskFragment)
    }

    companion object {
        const val EXTRA_TASK_ID = "TASK_ID"
        const val REQUEST_ADD_TASK = 1
    }
}
