package ru.trubin23.tasksmvpkotlin.statistics

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.NavUtils
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import ru.trubin23.tasksmvpkotlin.R
import ru.trubin23.tasksmvpkotlin.util.Injection
import ru.trubin23.tasksmvpkotlin.util.addFragmentToActivity

class StatisticsActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.statistics_act)

        supportActionBar?.run {
            setTitle(R.string.statistics)
            setHomeAsUpIndicator(R.drawable.ic_menu)
            setDisplayHomeAsUpEnabled(true)
        }

        mDrawerLayout = findViewById(R.id.drawer_layout)

        setupDrawerContent(findViewById(R.id.nav_view))

        val addEditTaskFragment = supportFragmentManager.findFragmentById(R.id.content_frame)
                as StatisticsFragment? ?: StatisticsFragment.newInstance().also {
            addFragmentToActivity(it, R.id.content_frame)
        }

        StatisticsPresenter(Injection.provideTasksRepository(applicationContext), addEditTaskFragment)
    }

    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.list_nav_menu_item){
                NavUtils.navigateUpFromSameTask(this@StatisticsActivity)
            }
            menuItem.isChecked = true
            mDrawerLayout.closeDrawers()
            true
        }
    }
}
