package ru.trubin23.tasksmvpkotlin.tasks

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import ru.trubin23.tasksmvpkotlin.R
import ru.trubin23.tasksmvpkotlin.statistics.StatisticsActivity
import ru.trubin23.tasksmvpkotlin.util.Injection
import ru.trubin23.tasksmvpkotlin.util.addFragmentToActivity

class TasksActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout

    private lateinit var mTasksPresenter: TasksPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tasks_act)

        supportActionBar?.run {
            setHomeAsUpIndicator(R.drawable.ic_menu)
            setDisplayHomeAsUpEnabled(true)
        }

        mDrawerLayout = findViewById(R.id.drawer_layout)

        setupDrawerContent(findViewById(R.id.nav_view))

        val tasksFragment = supportFragmentManager.findFragmentById(R.id.content_frame)
                as TasksFragment? ?: TasksFragment.newInstance().also {
            addFragmentToActivity(it, R.id.content_frame)
        }

        mTasksPresenter = TasksPresenter(Injection.provideTasksRepository(applicationContext),
                tasksFragment).apply {
            if (savedInstanceState != null) {
                mCurrentFiltering = savedInstanceState.getSerializable(CURRENT_FILTERING_KEY)
                        as TasksFilterType
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState.apply {
            putSerializable(CURRENT_FILTERING_KEY, mTasksPresenter.mCurrentFiltering)
        })
    }

    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.statistics_nav_menu_item) {
                val intent = Intent(this@TasksActivity, StatisticsActivity::class.java)
                startActivity(intent)
            }
            menuItem.isChecked = true
            mDrawerLayout.closeDrawers()
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val CURRENT_FILTERING_KEY = "CURRENT_FILTERING_KEY"
    }
}
