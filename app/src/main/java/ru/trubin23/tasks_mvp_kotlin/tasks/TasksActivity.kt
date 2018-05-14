package ru.trubin23.tasks_mvp_kotlin.tasks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ru.trubin23.tasks_mvp_kotlin.R

class TasksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tasks_act)

        val tasksFragment = supportFragmentManager.findFragmentById(R.id.content_frame)
                as TasksFragment? ?: TasksFragment.newInstance().also {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.content_frame, it)
            transaction.commit()
        }
    }
}
