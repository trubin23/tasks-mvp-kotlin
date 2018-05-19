package ru.trubin23.tasks_mvp_kotlin.tasks.task_list

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import ru.trubin23.tasks_mvp_kotlin.data.Task
import java.util.ArrayList

class TasksAdapter(private val itemListener: TaskItemListener)
    : BaseAdapter() {

    var mTasks : List<Task> = ArrayList(0)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItem(position: Int): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemId(position: Int): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}