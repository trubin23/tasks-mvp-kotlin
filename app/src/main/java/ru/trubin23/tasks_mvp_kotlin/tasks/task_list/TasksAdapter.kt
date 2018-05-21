package ru.trubin23.tasks_mvp_kotlin.tasks.task_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import ru.trubin23.tasks_mvp_kotlin.R
import ru.trubin23.tasks_mvp_kotlin.data.Task
import java.util.ArrayList

class TasksAdapter(private val itemListener: TaskItemListener)
    : BaseAdapter() {

    var mTasks : List<Task> = ArrayList(0)
        set(tasks) {
            field = tasks
            notifyDataSetChanged()
        }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = convertView ?: LayoutInflater.from(parent?.context)
                .inflate(R.layout.task_item, parent, false)
        val task = getItem(position)

        with(rowView.findViewById<TextView>(R.id.item_title)){
            text = task.titleForList
        }

        with(rowView.findViewById<CheckBox>(R.id.item_completed)){
            isChecked = task.isCompleted
        }

        rowView.setOnClickListener{ itemListener.onTaskClick(task)}

        return rowView
    }

    override fun getCount() = mTasks.size

    override fun getItem(position: Int) = mTasks[position]

    override fun getItemId(position: Int) = position.toLong()

}