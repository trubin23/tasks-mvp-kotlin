package ru.trubin23.tasks_mvp_kotlin.taskdetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ru.trubin23.tasks_mvp_kotlin.R
import ru.trubin23.tasks_mvp_kotlin.util.Injection
import ru.trubin23.tasks_mvp_kotlin.util.addFragmentToActivity

class TaskDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.taskdetail_act)

        val taskId = intent.getStringExtra(EXTRA_TASK_ID)

        val taskDetailFragment = supportFragmentManager.findFragmentById(R.id.content_frame)
                as TaskDetailFragment? ?: TaskDetailFragment.newInstance().also {
            addFragmentToActivity(it, R.id.content_frame)
        }

        TaskDetailPresenter(taskId, Injection.provideTasksRepository(applicationContext),
                taskDetailFragment)
    }

    companion object {
        const val EXTRA_TASK_ID = "TASK_ID"
    }
}
