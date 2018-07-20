package ru.trubin23.tasksmvpkotlin.tasks

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import ru.trubin23.tasksmvpkotlin.R
import ru.trubin23.tasksmvpkotlin.util.Injection
import ru.trubin23.tasksmvpkotlin.util.addFragmentToActivity

class TasksActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tasks_act)

        mDrawerLayout = findViewById(R.id.drawer_layout)

        val tasksFragment = supportFragmentManager.findFragmentById(R.id.content_frame)
                as TasksFragment? ?: TasksFragment.newInstance().also {
            addFragmentToActivity(it, R.id.content_frame)
        }

        TasksPresenter(Injection.provideTasksRepository(applicationContext), tasksFragment)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            mDrawerLayout.openDrawer(GravityCompat.START)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
