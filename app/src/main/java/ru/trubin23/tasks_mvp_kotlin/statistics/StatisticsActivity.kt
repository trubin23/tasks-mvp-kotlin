package ru.trubin23.tasks_mvp_kotlin.statistics

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ru.trubin23.tasks_mvp_kotlin.R
import ru.trubin23.tasks_mvp_kotlin.util.Injection
import ru.trubin23.tasks_mvp_kotlin.util.addFragmentToActivity

class StatisticsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.statistics_act)

        val addEditTaskFragment = supportFragmentManager.findFragmentById(R.id.content_frame)
                as StatisticsFragment ? ?: StatisticsFragment.newInstance().also {
            addFragmentToActivity(it, R.id.content_frame)
        }

        StatisticsPresenter(Injection.provideTasksRepository(applicationContext), addEditTaskFragment)
    }
}
