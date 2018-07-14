package ru.trubin23.tasksmvpkotlin.tasks

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ru.trubin23.tasksmvpkotlin.R
import ru.trubin23.tasksmvpkotlin.util.Injection
import ru.trubin23.tasksmvpkotlin.util.addFragmentToActivity

class TasksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tasks_act)

        val tasksFragment = supportFragmentManager.findFragmentById(R.id.content_frame)
                as TasksFragment? ?: TasksFragment.newInstance().also {
            addFragmentToActivity(it, R.id.content_frame)
        }

        TasksPresenter(Injection.provideTasksRepository(applicationContext), tasksFragment)
    }
}
