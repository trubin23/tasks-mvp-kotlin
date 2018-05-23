package ru.trubin23.tasks_mvp_kotlin.tasks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ru.trubin23.tasks_mvp_kotlin.R
import ru.trubin23.tasks_mvp_kotlin.util.addFragmentToActivity

class TasksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tasks_act)

        val tasksFragment = supportFragmentManager.findFragmentById(R.id.content_frame)
                as TasksFragment? ?: TasksFragment.newInstance().also {
            addFragmentToActivity(it, R.id.content_frame)
        }

        TasksPresenter(tasksFragment)
    }
}
